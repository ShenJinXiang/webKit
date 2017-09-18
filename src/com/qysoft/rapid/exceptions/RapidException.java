package com.qysoft.rapid.exceptions;

import org.apache.log4j.Logger;

/**
 * Rapid运行时异常
 * @author liugong
 *
 */
public class RapidException extends RuntimeException {

	private static final long serialVersionUID = 5754130615112869411L;

	public RapidException(String message) {
		super(message);
		Logger.getLogger(this.getClass()).error(message);
	}

	public RapidException(Throwable e) {
		super(e);
		Logger.getLogger(this.getClass()).error("RapidException异常", e);
	}

	public RapidException(String message,Throwable e) {
		super(message,e);
		Logger.getLogger(this.getClass()).error(message, e);
	}
}
