package peiliping.serializable;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONStreamAware;

public class ProfiledMethod implements JSONStreamAware {
	private final String className;
	private final String methodName;
	private final int lineNumber;

	private ProfiledMethod(String className, String methodName, int lineNumber) {
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
	}

	public static ProfiledMethod newProfiledMethod(StackTraceElement stackElement) {
		if (stackElement == null) {
			return null;
		}
		if (stackElement.getClassName() == null) {
			return null;
		}
		if (stackElement.getMethodName() == null) {
			return null;
		}

		return new ProfiledMethod(stackElement.getClassName(), stackElement.getMethodName(), stackElement.getLineNumber());
	}

	public String getFullMethodName() {
		return getClassName() + ":" + getMethodName();
	}

	public String getMethodName() {
		return this.methodName;
	}

	public String getClassName() {
		return this.className;
	}

	public final int getLineNumber() {
		return this.lineNumber;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + this.className.hashCode();
		result = prime * result + this.lineNumber;
		result = prime * result + this.methodName.hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProfiledMethod other = (ProfiledMethod) obj;
		if (this.lineNumber != other.lineNumber) {
			return false;
		}
		if (!this.methodName.equals(other.methodName)) {
			return false;
		}
		return this.className.equals(other.className);
	}

	public String toString() {
		return getFullMethodName() + ":" + this.lineNumber;
	}

	@Override
	public void writeJSONString(Writer out) throws IOException {
		JSONArray.writeJSONString(Arrays.asList(new Serializable[] { getClassName(), getMethodName(), Integer.valueOf(getLineNumber()) }), out);
	}
}