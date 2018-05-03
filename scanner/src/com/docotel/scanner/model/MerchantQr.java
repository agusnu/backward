package com.docotel.scanner.model;

import android.text.TextUtils;


import java.util.ArrayList;
import java.util.List;

import static com.docotel.scanner.model.CreditCardValidator.format;

public class MerchantQr {
    private String qr;
    private int currentIdx = 0;
    private boolean isValid = false;
    private List<MerchantQrTag> qrByTag = new ArrayList<>();
    private String qrVersion = "";
    private QrType type = null;
    private String visaId = "";
    private String mastercardId = "";
    private String emoneyId = "";
    private String debitId = "";
    private String merchantData = "";
    private String defaultId = "";
    private String merchantCategoryCode = "";
    private String currencyCode = "";
    private String amount = "";
    private Tip tip = Tip.PROMPT;
    private String vTip = "";
    private String fixedFee = "0";
    private String percentageFee = "";
    private String countryCode = "";
    private String merchantName = "";
    private String merchantCode = "";
    private String merchantType = "";
    private String merchantCity = "";
    private String postalCode = "";
    private String domain = "";
    private String dataBank = "";
    private String additionalMerchant = "";
    private String tipeMerchant = "";
    private String additionalDataField = "";
    private String billNumber = "";
    private String referenceId = "";
    private String crc = "";
    private boolean isAdditionalField = false;
    private String network = "";

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getCurrentIdx() {
        return currentIdx;
    }

    public void setCurrentIdx(int currentIdx) {
        this.currentIdx = currentIdx;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<MerchantQrTag> getQrByTag() {
        return qrByTag;
    }

    public void setQrByTag(List<MerchantQrTag> qrByTag) {
        this.qrByTag = qrByTag;
    }

    public String getQrVersion() {
        return qrVersion;
    }

    public void setQrVersion(String qrVersion) {
        this.qrVersion = qrVersion;
    }

    public QrType getType() {
        return type;
    }

    public void setType(QrType type) {
        this.type = type;
    }

    public void setVisaId(String visaId) {
        this.visaId = visaId;
    }

    public void setMastercardId(String mastercardId) {
        this.mastercardId = mastercardId;
    }

    public void setEmoneyId(String emoneyId) {
        this.emoneyId = emoneyId;
    }

    public void setDebitId(String debitId) {
        this.debitId = debitId;
    }

    public void setMerchantData(String merchantData) {
        this.merchantData = merchantData;
    }

    public void setDefaultId(String defaultId) {
        this.defaultId = defaultId;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(String fixedFee) {
        this.fixedFee = fixedFee;
    }

    public String getPercentageFee() {
        return percentageFee;
    }

    public void setPercentageFee(String percentageFee) {
        this.percentageFee = percentageFee;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDataBank() {
        return dataBank;
    }

    public void setDataBank(String dataBank) {
        this.dataBank = dataBank;
    }

    public String getAdditionalMerchant() {
        return additionalMerchant;
    }

    public void setAdditionalMerchant(String additionalMerchant) {
        this.additionalMerchant = additionalMerchant;
    }

    public String getTipeMerchant() {
        return tipeMerchant;
    }

    public void setTipeMerchant(String tipeMerchant) {
        this.tipeMerchant = tipeMerchant;
    }

    public String getAdditionalDataField() {
        return additionalDataField;
    }

    public void setAdditionalDataField(String additionalDataField) {
        this.additionalDataField = additionalDataField;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public boolean isAdditionalField() {
        return isAdditionalField;
    }

    public void setAdditionalField(boolean additionalField) {
        isAdditionalField = additionalField;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public MerchantQr(String qr) {
        this.qr = qr;
        crcCheck();
    }

    private MerchantQr(String qr, boolean isAdditionalField) {
        this.qr = qr;
        this.isAdditionalField = isAdditionalField;
        parseByTag();
    }

    private void crcCheck() {
        try {
            String plain = qr.substring(0, qr.length() - 4);
            String crc = qr.substring(qr.length() - 4, qr.length()).toLowerCase();
            String validCrc = CrcHelper.crc(plain).toLowerCase();
            if (validCrc.length() == 3) validCrc = "0" + validCrc;
            if (validCrc.length() == 2) validCrc = "00" + validCrc;
            if (validCrc.length() == 1) validCrc = "000" + validCrc;
            LoggerHelper.info(validCrc);
            if (validCrc.equals(crc)) parseByTag();
        } catch (Exception ignored) { }
    }

    private List<MerchantQrTag> getFields() {
        if (isAdditionalField) return qrByTag;
        else return null;
    }

    private void parseByTag() {
        try {
            if (currentIdx == qr.length()) {
                isValid = true;
                if (!isAdditionalField) readEachTag();
                return;
            }
            MerchantQrTag qrTag = new MerchantQrTag();
            qrTag.setTag(qr.substring(currentIdx, currentIdx + 2));
            if (TextUtils.isDigitsOnly(qr.substring(currentIdx + 2, currentIdx + 4)))
                qrTag.setLength(Integer.valueOf(qr.substring(currentIdx + 2, currentIdx + 4)));
            qrTag.setValue(qr.substring(currentIdx + 4, currentIdx + 4 + qrTag.getLength()));
            currentIdx = currentIdx + 4 + qrTag.getLength();
            qrByTag.add(qrTag);
            parseByTag();
        } catch (Exception e) {
            e.printStackTrace();
            isValid = false;
        }
    }

    private void readEachTag() {
        for (MerchantQrTag qrTag : qrByTag) {
            setValue(qrTag.getTag(), qrTag.getValue());
        }
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getvTip() {
        return vTip;
    }

    public void setvTip(String vTip) {
        this.vTip = vTip;
    }

    public String getVisa_id() {
        return visaId;
    }

    public String getMastercard_id() {
        return mastercardId;
    }

    public String getVisaId() {
        return format(visaId);
    }

    public String getMastercardId() {
        return format(mastercardId);
    }

    public String getEmoneyId() {
        return format(emoneyId);
    }

    public String getDebitId() {
        return format(debitId);
    }

    public String getMerchantData() {
        return merchantData;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public void setDefaultId(MerchantNetwork network) {
        switch (network) {
            case VISA:
                defaultId = visaId;
                this.network = "Visa";
                break;
            case MASTERCARD:
                defaultId = mastercardId;
                this.network = "Master";
                break;
            case EMONEY:
                defaultId = emoneyId;
                this.network = "Emoney";
                break;
            case DEBIT:
                defaultId = debitId;
                this.network = "Debit";
                break;
            case NEWQR:
                defaultId = dataBank;
                this.network = "Debit";
                break;
        }
    }

    public boolean hasVisaPan() {
        return !TextUtils.isEmpty(visaId);
    }

    public boolean hasMasterCardPan() {
        return !TextUtils.isEmpty(mastercardId);
    }

    public boolean hasEmoneyPan() {
        return !TextUtils.isEmpty(emoneyId);
    }

    public boolean hasDebitPan() {
        return !TextUtils.isEmpty(debitId);
    }

    public String getAcquiringBin() {
        return defaultId.subSequence(0, 6).toString();
    }

    public String getDefaultId() {
        return defaultId;
    }

    private void setValue(String tag, String value) {
        switch (tag) {
            case "00":
                qrVersion = value;
                break;
            case "01":
                if (value.equals("11")) type = QrType.STATIC;
                if (value.equals("12")) type = QrType.DYNAMIC;
                break;
            case "02":
                visaId = value;
                break;
            case "03":
            case "04":
//                mastercardId = LuhnHelper.getValidCC(value);
                mastercardId = value;
                break;
            case "05":
                referenceId = value;
                break;
            case "26":
                if(value.length()<=16) {
                    debitId = value;
                } else {
                    merchantData = value;
                }
                break;
            case "33":
                emoneyId = value;
                break;
            case "35":
                merchantCode = value;
                break;
            case "52":
                merchantType = value;
                break;
            /*case "52":
                merchantCategoryCode = value;
                break;*/
            case "53":
                currencyCode = value;
                break;
            case "54":
                amount = value;
                break;
            case "55":
                vTip = value;
                if (value.equals("02")) tip = Tip.FLAT;
                if (value.equals("03")) tip = Tip.PERCENTAGE;
                break;
            case "56":
                fixedFee = value;
                break;
            case "57":
                percentageFee = value;
                break;
            case "58":
                countryCode = value;
                break;
            case "59":
                merchantName = value;
                break;
            case "60":
                merchantCity = value;
                break;
            case "61":
                postalCode = value;
                break;
            case "62":
                additionalDataField = value;
                break;
            case "63":
                crc = value;
        }
        validate();
    }

    private void validate() {
        readAdditionalField();
        readEmoneyCard();
        if(!merchantData.isEmpty())readMerchantData();
//        readDebitCard();
    }

    private void readMerchantData() {
        MerchantQr additionalField = new MerchantQr(merchantData, true);
        List<MerchantQrTag> additionalTags = additionalField.getFields();
        if (additionalTags != null) {
            for (MerchantQrTag additionalTag: additionalTags) {
                switch (additionalTag.getTag()) {
                    case "00":
                        domain = additionalTag.getValue();
                        break;
                    case "01":
                        dataBank = additionalTag.getValue();
                    case "02":
                        additionalMerchant = additionalTag.getValue();
                    case "03":
                        tipeMerchant = additionalTag.getValue();
                }
            }
        }
    }

    private void readAdditionalField() {
        MerchantQr additionalField = new MerchantQr(additionalDataField, true);
        List<MerchantQrTag> additionalTags = additionalField.getFields();
        if (additionalTags != null) {
            for (MerchantQrTag additionalTag: additionalTags) {
                switch (additionalTag.getTag()) {
                    case "01":
                        billNumber = additionalTag.getValue();
                        break;
                    case "05":
                        referenceId = additionalTag.getValue();
                }
            }
        }
    }

    private void readEmoneyCard() {
        MerchantQr additionalField = new MerchantQr(emoneyId, true);
        List<MerchantQrTag> additionalTags = additionalField.getFields();
        if (additionalTags != null) {
            for (MerchantQrTag additionalTag: additionalTags) {
                switch (additionalTag.getTag()) {
                    case "01":
                        emoneyId = additionalTag.getValue();
                }
            }
        }
    }
    private void readDebitCard() {
        MerchantQr additionalField = new MerchantQr(debitId, true);
        List<MerchantQrTag> additionalTags = additionalField.getFields();
        if (additionalTags != null) {
            for (MerchantQrTag additionalTag: additionalTags) {
                switch (additionalTag.getTag()) {
                    case "01":
                        debitId = additionalTag.getValue();
                }
            }
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public enum QrType { STATIC, DYNAMIC }

    private enum Tip { PROMPT, FLAT, PERCENTAGE }

    public enum MerchantNetwork { VISA, MASTERCARD, EMONEY, DEBIT, NEWQR }
}
