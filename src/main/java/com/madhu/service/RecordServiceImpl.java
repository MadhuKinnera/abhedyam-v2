package com.madhu.service;

import com.madhu.dto.RecordDTO;
import com.madhu.dto.RecordResponseModel;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.Product;
import com.madhu.entity.Remainder;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.CustomerException;
import com.madhu.exception.ProductException;
import com.madhu.exception.SaleRecordException;
import com.madhu.exception.RemainderException;
import com.madhu.exception.TransactionException;
import com.madhu.exception.VillageException;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.OccasionRepo;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.RecordRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;
import com.madhu.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepo recordRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CommonUtils utils;
    @Autowired
    private OccasionRepo occasionRepo;

    @Autowired
    private UserInfo userInfo;

    @Override
    public SaleRecord addRecord(RecordDTO dto) throws SaleRecordException, ProductException, CustomerException {

        var saleRecord = new SaleRecord();

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new ProductException(Constants.PRODUCT_ID_NOT_FOUND + dto.getProductId()));

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerException(Constants.CUSTOMER_ID_NOT_FOUND + dto.getCustomerId()));

        saleRecord.setCustomer(customer);
        saleRecord.setProduct(product);
        saleRecord.setQuantity((dto.getQuantity() == null || dto.getQuantity() == 0) ? 1 : dto.getQuantity());

        saleRecord.setTotalAmount((dto.getTotalAmount() == null || dto.getTotalAmount() == 0)
                ? product.getSellingPrice() * (dto.getQuantity() == 0 ? 1 : dto.getQuantity())
                : dto.getTotalAmount() * dto.getQuantity());

        saleRecord.setStartDate(LocalDate.now());
        saleRecord.setTimestamp(LocalDateTime.now());
        saleRecord.setDueAmount(saleRecord.getTotalAmount());
        var occasion = occasionRepo.findById(dto.getOccasionId())
                .orElseThrow(() -> new SaleRecordException("No Occasion Found with id " + dto.getOccasionId()));
        saleRecord.setOccasion(occasion);
        saleRecord.setEndDate(dto.getEndDate());
        saleRecord.setDescription(dto.getDescription());
        return recordRepo.save(saleRecord);
    }

    @Override
    public SaleRecord updateRecord(Integer recordId, SaleRecord saleRecord) throws SaleRecordException {

        if (!utils.isRecordExist(recordId))
            throw new SaleRecordException(Constants.RECORD_ID_NOT_FOUND + recordId);

        return recordRepo.save(saleRecord);
    }

    @Override
    public SaleRecord getRecordByRecordId(Integer recordId) throws SaleRecordException {

        return recordRepo.findById(recordId)
                .orElseThrow(() -> new SaleRecordException(Constants.RECORD_ID_NOT_FOUND + recordId));
    }

    @Override
    public List<SaleRecord> getRecordByCustomerId(Integer customerId) throws SaleRecordException {

        List<SaleRecord> records = recordRepo.findByCustomerCustomerIdAndCustomerUserUserId(customerId, userInfo.getUserId());

        if (records.isEmpty())
            throw new SaleRecordException(Constants.NO_RECORDS_FOUND_WITH_CUSTOMER_ID + customerId);

        return records;

    }

    @Override
    public List<SaleRecord> getRecordRecordResponseByRank() throws SaleRecordException {

        List<SaleRecord> records = recordRepo.findAll();

        if (records.isEmpty())
            throw new SaleRecordException("No Records Found ");

        return records;
    }

    @Override
    public SaleRecord deleteRecordByRecordId(Integer recordId) throws SaleRecordException {
        SaleRecord record = getRecordByRecordId(recordId);
        recordRepo.delete(record);
        return record;
    }

    @Override
    public List<SaleRecord> getRecordsByVillageId(Integer villageId) throws VillageException {
        List<SaleRecord> records = recordRepo.findByCustomerAddressVillageVillageIdAndCustomerUserUserId(villageId,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new VillageException("No Records Found with Village Id " + villageId);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsByVillageName(String villageName) throws VillageException {
        List<SaleRecord> records = recordRepo.findByCustomerAddressVillageVillageNameAndCustomerUserUserId(villageName,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new VillageException("No Records Found with Village Name " + villageName);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsByMandal(String mandal) throws VillageException {
        List<SaleRecord> records = recordRepo.findByCustomerAddressVillageMandalAndCustomerUserUserId(mandal,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new VillageException("No Records Found with Mandal  " + mandal);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsByPincode(Integer pincode) throws VillageException {
        List<SaleRecord> records = recordRepo.findByCustomerAddressVillagePincodeAndCustomerUserUserId(pincode,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new VillageException("No Records Found with Pincode " + pincode);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsByDistrict(String district) throws VillageException {
        List<SaleRecord> records = recordRepo.findByCustomerAddressVillageDistrictAndCustomerUserUserId(district,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new VillageException("No Records Found with District " + district);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsByCustomerId(Integer customerId) throws CustomerException {

        List<SaleRecord> records = recordRepo.findByCustomerCustomerIdAndCustomerUserUserId(customerId, userInfo.getUserId());

        if (records.isEmpty())
            throw new CustomerException("No Records Found with Customer Id " + customerId);

        return records;

    }

    @Override
    public List<Transaction> getTransactionsByRecordId(Integer recordId) throws SaleRecordException, TransactionException {

        SaleRecord record = getRecordByRecordId(recordId);

        if (record.getTransactions().isEmpty())
            throw new TransactionException("No Transactions Found with Record Id " + recordId);

        return record.getTransactions();

    }

    @Override
    public List<SaleRecord> getRecordsByProductId(Integer productId) throws SaleRecordException {
        List<SaleRecord> records = recordRepo.findByProductProductIdAndCustomerUserUserId(productId, userInfo.getUserId());

        if (records.isEmpty())
            throw new SaleRecordException("No Records Found with Product Id " + productId);

        return records;

    }

    @Override
    public List<SaleRecord> getRecordsByProductName(String productName) throws SaleRecordException {
        List<SaleRecord> records = recordRepo.findByProductProductNameAndCustomerUserUserId(productName, userInfo.getUserId());

        if (records.isEmpty())
            throw new SaleRecordException("No Records Found with Product Name " + productName);

        return records;
    }

    @Override
    public List<Remainder> getRemaindersByRecordId(Integer recordId) throws RemainderException, SaleRecordException {
        SaleRecord record = getRecordByRecordId(recordId);

        if (record.getRemainders().isEmpty())
            throw new RemainderException("No Transactions Found with Record Id " + recordId);

        return record.getRemainders();
    }

    @Override
    public List<SaleRecord> getRecordsByEmail(String email) throws CustomerException {

        List<SaleRecord> records = recordRepo.findByCustomerEmailAndCustomerUserUserId(email, userInfo.getUserId());

        if (records.isEmpty())
            throw new CustomerException("No Records Found with Customer Email " + email);

        return records;

    }

    @Override
    public List<SaleRecord> getRecordsByCustomerName(String name) throws CustomerException {
        List<SaleRecord> records = recordRepo.findByCustomerCustomerNameAndCustomerUserUserId(name, userInfo.getUserId());

        if (records.isEmpty())
            throw new CustomerException("No Records Found with Customer Name " + name);

        return records;
    }

    @Override
    public Village getVillageByRecordId(Integer recordId) throws SaleRecordException {

        SaleRecord record = getRecordByRecordId(recordId);

        return record.getCustomer().getAddress().getVillage();
    }

    @Override
    public Address getAddressByRecordId(Integer recordId) throws SaleRecordException {
        SaleRecord record = getRecordByRecordId(recordId);

        return record.getCustomer().getAddress();
    }

    @Override
    public Product getProductByRecordId(Integer recordId) throws SaleRecordException {
        SaleRecord record = getRecordByRecordId(recordId);

        return record.getProduct();
    }

    @Override
    public List<SaleRecord> getRecordsBetweenStartDateTimeStamps(LocalDate fromDate, LocalDate toDate)
            throws CustomerException {
        List<SaleRecord> records = recordRepo.findByStartDateBetweenAndCustomerUserUserId(fromDate, toDate,
                userInfo.getUserId());

        if (records.isEmpty())
            throw new CustomerException("No Records Found from" + fromDate + " and " + toDate);

        return records;
    }

    @Override
    public List<SaleRecord> getRecordsBetweenEndDateTimeStamps(LocalDate fromDate, LocalDate toDate)
            throws CustomerException {
        List<SaleRecord> records = recordRepo.findByEndDateBetweenAndCustomerUserUserId(fromDate, toDate, userInfo.getUserId());

        if (records.isEmpty())
            throw new CustomerException("No Records Found from" + fromDate + " and " + toDate);

        return records;
    }

    @Override
    public Customer getCustomerByRecordId(Integer recordId) throws SaleRecordException {
        SaleRecord record = getRecordByRecordId(recordId);

        return record.getCustomer();
    }

    @Override
    public List<RecordResponseModel> getRecordResponseModels() throws SaleRecordException {

        var recordResponseModels = new ArrayList<RecordResponseModel>();

        List<SaleRecord> records = recordRepo.findByCustomerUserUserId(userInfo.getUserId());

        if (records.isEmpty())
            throw new SaleRecordException(Constants.RECORDS_NOT_FOUND);

        records.sort((r1, r2) -> Integer.compare(r2.getDueAmount(), r1.getDueAmount()));

        for (var record : records) {

            var recordResponseModel = new RecordResponseModel();
            recordResponseModel.setSaleRecord(record);
            recordResponseModel.setCustomer(record.getCustomer());
            recordResponseModel.setVillage(record.getCustomer().getAddress().getVillage());

            recordResponseModels.add(recordResponseModel);

        }

        return recordResponseModels;

    }

    @Override
    public RecordResponseModel getRecordResponseModelByrecordId(Integer recordId) throws SaleRecordException {

        var record = recordRepo.findByCustomerUserUserIdAndRecordId(userInfo.getUserId(), recordId)
                .orElseThrow(() -> new SaleRecordException(Constants.RECORD_ID_NOT_FOUND + recordId));

        var recordResponseModel = new RecordResponseModel();
        recordResponseModel.setSaleRecord(record);
        recordResponseModel.setCustomer(record.getCustomer());
        recordResponseModel.setVillage(record.getCustomer().getAddress().getVillage());

        return recordResponseModel;
    }

    @Override
    public List<RecordResponseModel> getRecordsContainingRecordIdOrCustomerNameOrProductName(Integer recordId,
                                                                                             String customerName, String productName) throws SaleRecordException {

        var recordResponseModels = new ArrayList<RecordResponseModel>();

        List<SaleRecord> records = recordRepo
                .findByCustomerUserUserIdAndRecordIdOrCustomerCustomerNameIgnoreCaseContainingOrProductProductNameIgnoreCaseContaining(
                        userInfo.getUserId(), recordId, customerName, productName);

        if (records.isEmpty())
            throw new SaleRecordException("Records Not Found with Keyword Or Id " + productName);

        records.sort((r1, r2) -> Integer.compare(r2.getDueAmount(), r1.getDueAmount()));

        for (var record : records) {

            var recordResponseModel = new RecordResponseModel();
            recordResponseModel.setSaleRecord(record);
            recordResponseModel.setCustomer(record.getCustomer());
            recordResponseModel.setVillage(record.getCustomer().getAddress().getVillage());

            recordResponseModels.add(recordResponseModel);

        }

        return recordResponseModels;

    }

}
