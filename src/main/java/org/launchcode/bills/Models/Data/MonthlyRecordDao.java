package org.launchcode.bills.Models.Data;

import org.launchcode.bills.Models.MonthlyBill;
import org.launchcode.bills.Models.MonthlyRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MonthlyRecordDao extends CrudRepository<MonthlyRecord, Integer> {
}