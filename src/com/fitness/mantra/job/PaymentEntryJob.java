package com.fitness.mantra.job;

import com.fitness.mantra.dao.PaymentsDao;

public class PaymentEntryJob implements Runnable {

	private final PaymentsDao paymentsDao = new PaymentsDao();

	@Override
	public void run() {
		System.out.println("Added new payment entries " + paymentsDao.updateCurrentMonthEntries());
	}

}
