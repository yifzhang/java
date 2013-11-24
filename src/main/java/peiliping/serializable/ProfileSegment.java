package peiliping.serializable;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONStreamAware;

public class ProfileSegment implements JSONStreamAware {
	private final ProfiledMethod method;
	private int runnableCallCount = 0;
	private int nonrunnableCallCount = 0;
	private final Map<ProfiledMethod, ProfileSegment> children = new HashMap<ProfiledMethod, ProfileSegment>();

	private ProfileSegment(ProfiledMethod method) {
		this.method = method;
	}

	public static ProfileSegment newProfileSegment(ProfiledMethod method) {
		if (method == null) {
			return null;
		}

		return new ProfileSegment(method);
	}

	public void writeJSONString(Writer out) throws IOException {
		JSONArray.writeJSONString(
				Arrays.asList(new Object[] { this.method,
						Integer.valueOf(this.runnableCallCount),
						Integer.valueOf(this.nonrunnableCallCount),
						new ArrayList<ProfileSegment>(this.children.values()) }), out);
	}

	public String toString() {
		return this.method.toString();
	}

	public ProfiledMethod getMethod() {
		return this.method;
	}

	protected int getRunnableCallCount() {
		return this.runnableCallCount;
	}

	public void incrementRunnableCallCount() {
		this.runnableCallCount += 1;
	}

	public void incrementNonRunnableCallCount() {
		this.nonrunnableCallCount += 1;
	}

	Collection<ProfileSegment> getChildren() {
		return this.children.values();
	}

	ProfileSegment addChild(ProfiledMethod method) {
		ProfileSegment result = (ProfileSegment) this.children.get(method);
		if (result == null) {
			result = newProfileSegment(method);
			this.children.put(method, result);
		}
		return result;
	}

	void removeChild(ProfiledMethod method) {
		this.children.remove(method);
	}

	public int getCallSiteCount() {
		int count = 1;

		for (ProfileSegment segment : this.children.values()) {
			count += segment.getCallSiteCount();
		}

		return count;
	}

	public int getCallCount(ProfiledMethod method) {
		int count = method.equals(getMethod()) ? this.runnableCallCount : 0;
		for (ProfileSegment kid : this.children.values()) {
			count += kid.getCallCount(method);
		}

		return count;
	}

	public Set<ProfiledMethod> getMethods() {
		Set<ProfiledMethod> methods = new HashSet<ProfiledMethod>();
		methods.add(getMethod());
		for (ProfileSegment kid : this.children.values()) {
			methods.addAll(kid.getMethods());
		}
		return methods;
	}
}