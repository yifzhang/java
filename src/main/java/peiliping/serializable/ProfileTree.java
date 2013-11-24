package peiliping.serializable;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONStreamAware;

public class ProfileTree implements JSONStreamAware {
	private final Map<ProfiledMethod, ProfileSegment> rootSegments = new HashMap<ProfiledMethod, ProfileSegment>();
	private long cpuTime;

	private ProfileSegment add(StackTraceElement stackTraceElement, ProfileSegment parent, boolean runnable) {
		ProfiledMethod method = ProfiledMethod.newProfiledMethod(stackTraceElement);
		if (method == null) {
			return parent;
		}

		return add(method, parent, runnable);
	}

	private ProfileSegment add(ProfiledMethod method, ProfileSegment parent, boolean runnable) {
		ProfileSegment result = add(method, parent);
		if (runnable) {
			result.incrementRunnableCallCount();
		} else {
			result.incrementNonRunnableCallCount();
		}

		return result;
	}

	private ProfileSegment add(ProfiledMethod method, ProfileSegment parent) {
		ProfileSegment result;
		if (parent == null) {
			result = (ProfileSegment) this.rootSegments.get(method);
			if (result == null) {
				result = ProfileSegment.newProfileSegment(method);
				this.rootSegments.put(method, result);
			}
		} else {
			result = parent.addChild(method);
		}
		return result;
	}

	public int getCallCount(StackTraceElement stackElement) {
		ProfiledMethod method = ProfiledMethod.newProfiledMethod(stackElement);
		if (method == null) {
			return 0;
		}

		int count = 0;
		for (ProfileSegment segment : this.rootSegments.values()) {
			count += segment.getCallCount(method);
		}
		return count;
	}

	public int getCallSiteCount() {
		int count = 0;
		for (ProfileSegment segment : this.rootSegments.values()) {
			count += segment.getCallSiteCount();
		}
		return count;
	}

	public Collection<ProfileSegment> getRootSegments() {
		return this.rootSegments.values();
	}

	public int getRootCount() {
		return getRootSegments().size();
	}

	public int getMethodCount() {
		Set<ProfiledMethod> methodNames = new HashSet<ProfiledMethod>();
		for (ProfileSegment segment : this.rootSegments.values()) {
			methodNames.addAll(segment.getMethods());
		}
		return methodNames.size();
	}

	public void addStackTrace(List<StackTraceElement> stackTraceList, boolean runnable) {
		ProfileSegment parent = null;
		for (StackTraceElement methodCall : stackTraceList)
			parent = add(methodCall, parent, runnable);
	}

	public void writeJSONString(Writer out) throws IOException {
		Collection<ProfileSegment> rootSegments = getRootSegments();
		ArrayList<Object> list = new ArrayList<Object>(rootSegments.size() + 1);
		list.add(getExtraData());
		list.addAll(rootSegments);
		JSONArray.writeJSONString(list, out);
	}

	private Map<String, Object> getExtraData() {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("cpu_time", Long.valueOf(this.cpuTime));

		return data;
	}

	public void incrementCpuTime(long cpuTime) {
		this.cpuTime += cpuTime;
	}

	public long getCpuTime() {
		return this.cpuTime;
	}
}