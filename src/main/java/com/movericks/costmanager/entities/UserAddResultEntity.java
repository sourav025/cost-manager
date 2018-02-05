package com.movericks.costmanager.entities;

import java.util.List;

public class UserAddResultEntity {
	private long successful;
	private long failed;
	private List<String> failedLists;

	public UserAddResultEntity() {
	}

	public UserAddResultEntity(long successful, long failed,
			List<String> failedLists) {
		this.successful = successful;
		this.failed = failed;
		this.failedLists = failedLists;
	}

	/**
	 * @return the successful
	 */
	public long getSuccessful() {
		return successful;
	}

	/**
	 * @param successful
	 *            the successful to set
	 */
	public void setSuccessful(long successful) {
		this.successful = successful;
	}

	/**
	 * @return the failed
	 */
	public long getFailed() {
		return failed;
	}

	/**
	 * @param failed
	 *            the failed to set
	 */
	public void setFailed(long failed) {
		this.failed = failed;
	}

	/**
	 * @return the failedLists
	 */
	public List<String> getFailedLists() {
		return failedLists;
	}

	/**
	 * @param failedLists
	 *            the failedLists to set
	 */
	public void setFailedLists(List<String> failedLists) {
		this.failedLists = failedLists;
	}
}
