package com.madhu.service;

import java.time.LocalDate;
import java.util.List;

import com.madhu.dto.RecordDTO;
import com.madhu.dto.RecordResponseModel;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.SaleRecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;

public interface RecordService {

	SaleRecord addRecord(RecordDTO dto) throws SaleRecordException,CustomerException,ProductException;

	SaleRecord updateRecord(Integer recordId, SaleRecord saleRecord) throws SaleRecordException;

	SaleRecord getRecordByRecordId(Integer recordId) throws SaleRecordException;

	List<SaleRecord> getRecordByCustomerId(Integer customerId) throws CustomerException, SaleRecordException;

	List<SaleRecord> getRecordRecordResponseByRank() throws SaleRecordException;

	// ==============================

	SaleRecord deleteRecordByRecordId(Integer recordId) throws SaleRecordException;

	List<SaleRecord> getRecordsBetweenStartDateTimeStamps(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, SaleRecordException;
	
	List<SaleRecord> getRecordsBetweenEndDateTimeStamps(LocalDate fromDate, LocalDate toDate)
			throws CustomerException, SaleRecordException;

	List<SaleRecord> getRecordsByVillageId(Integer villageId) throws VillageException, SaleRecordException;

	List<SaleRecord> getRecordsByVillageName(String villageName) throws VillageException, SaleRecordException;

	List<SaleRecord> getRecordsByMandal(String mandal) throws VillageException, SaleRecordException;

	List<SaleRecord> getRecordsByPincode(Integer pincode) throws VillageException, SaleRecordException;

	List<SaleRecord> getRecordsByDistrict(String district) throws VillageException, SaleRecordException;

	List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException, SaleRecordException;

	List<Transaction> getTransactionsByRecordId(Integer recordId) throws SaleRecordException, TransactionException;

	List<SaleRecord> getRecordsByProductId(Integer productId) throws SaleRecordException, ProductException;

	List<SaleRecord> getRecordsByProductName(String productName) throws SaleRecordException, ProductException;

	List<Remainder> getRemaindersByRecordId(Integer recordId) throws RemainderException, SaleRecordException;

	List<SaleRecord> getRecordsByEmail(String email) throws CustomerException, SaleRecordException;

	List<SaleRecord> getRecordsByCustomerName(String name) throws CustomerException, SaleRecordException;

	Village getVillageByRecordId(Integer recordId) throws VillageException, SaleRecordException;

	Address getAddressByRecordId(Integer recordId) throws AddressException, SaleRecordException;

	Product getProductByRecordId(Integer recordId) throws ProductException, SaleRecordException;
	
	Customer getCustomerByRecordId(Integer recordId) throws SaleRecordException;

	//record response models
	List<RecordResponseModel> getRecordResponseModels() throws SaleRecordException;
	
	RecordResponseModel getRecordResponseModelByrecordId(Integer recordId) throws SaleRecordException;
	
	List<RecordResponseModel> getRecordsContainingRecordIdOrCustomerNameOrProductName(Integer recordId,String customerName,String productName) throws SaleRecordException;
	
	
	
}
