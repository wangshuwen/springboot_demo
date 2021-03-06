package com.zkxh.demo.model.staff;

import java.util.Date;

public class Staff {

    private StaffDept staffDept;

    public StaffDept getStaffDept() {
        return staffDept;
    }

    public void setStaffDept(StaffDept staffDept) {
        this.staffDept = staffDept;
    }

    private StaffGroup staffGroup;

    public StaffGroup getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(StaffGroup staffGroup) {
        this.staffGroup = staffGroup;
    }


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_id
     *
     * @mbg.generated
     */
    private Integer staffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_number
     *
     * @mbg.generated
     */
    private String staffNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_name
     *
     * @mbg.generated
     */
    private String staffName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_sex
     *
     * @mbg.generated
     */
    private Integer staffSex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_birthday
     *
     * @mbg.generated
     */
    private Date staffBirthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_id_card
     *
     * @mbg.generated
     */
    private String staffIdCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_wedlock
     *
     * @mbg.generated
     */
    private String staffWedlock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_email
     *
     * @mbg.generated
     */
    private String staffEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_address
     *
     * @mbg.generated
     */
    private String staffAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_phone
     *
     * @mbg.generated
     */
    private String staffPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_job_id
     *
     * @mbg.generated
     */
    private Integer staffJobId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_native_place
     *
     * @mbg.generated
     */
    private String staffNativePlace;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.staff_type_id
     *
     * @mbg.generated
     */
    private Integer staffTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.is_person
     *
     * @mbg.generated
     */
    private Integer isPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column staff.group_id
     *
     * @mbg.generated
     */
    private Integer groupId;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_id
     *
     * @return the value of staff.staff_id
     *
     * @mbg.generated
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_id
     *
     * @param staffId the value for staff.staff_id
     *
     * @mbg.generated
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_number
     *
     * @return the value of staff.staff_number
     *
     * @mbg.generated
     */
    public String getStaffNumber() {
        return staffNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_number
     *
     * @param staffNumber the value for staff.staff_number
     *
     * @mbg.generated
     */
    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_name
     *
     * @return the value of staff.staff_name
     *
     * @mbg.generated
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_name
     *
     * @param staffName the value for staff.staff_name
     *
     * @mbg.generated
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Integer getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(Integer staffSex) {
        this.staffSex = staffSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_birthday
     *
     * @return the value of staff.staff_birthday
     *
     * @mbg.generated
     */
    public Date getStaffBirthday() {
        return staffBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_birthday
     *
     * @param staffBirthday the value for staff.staff_birthday
     *
     * @mbg.generated
     */
    public void setStaffBirthday(Date staffBirthday) {
        this.staffBirthday = staffBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_id_card
     *
     * @return the value of staff.staff_id_card
     *
     * @mbg.generated
     */
    public String getStaffIdCard() {
        return staffIdCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_id_card
     *
     * @param staffIdCard the value for staff.staff_id_card
     *
     * @mbg.generated
     */
    public void setStaffIdCard(String staffIdCard) {
        this.staffIdCard = staffIdCard == null ? null : staffIdCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_wedlock
     *
     * @return the value of staff.staff_wedlock
     *
     * @mbg.generated
     */
    public String getStaffWedlock() {
        return staffWedlock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_wedlock
     *
     * @param staffWedlock the value for staff.staff_wedlock
     *
     * @mbg.generated
     */
    public void setStaffWedlock(String staffWedlock) {
        this.staffWedlock = staffWedlock == null ? null : staffWedlock.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_email
     *
     * @return the value of staff.staff_email
     *
     * @mbg.generated
     */
    public String getStaffEmail() {
        return staffEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_email
     *
     * @param staffEmail the value for staff.staff_email
     *
     * @mbg.generated
     */
    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail == null ? null : staffEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_address
     *
     * @return the value of staff.staff_address
     *
     * @mbg.generated
     */
    public String getStaffAddress() {
        return staffAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_address
     *
     * @param staffAddress the value for staff.staff_address
     *
     * @mbg.generated
     */
    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress == null ? null : staffAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_phone
     *
     * @return the value of staff.staff_phone
     *
     * @mbg.generated
     */
    public String getStaffPhone() {
        return staffPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_phone
     *
     * @param staffPhone the value for staff.staff_phone
     *
     * @mbg.generated
     */
    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone == null ? null : staffPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_job_id
     *
     * @return the value of staff.staff_job_id
     *
     * @mbg.generated
     */
    public Integer getStaffJobId() {
        return staffJobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_job_id
     *
     * @param staffJobId the value for staff.staff_job_id
     *
     * @mbg.generated
     */
    public void setStaffJobId(Integer staffJobId) {
        this.staffJobId = staffJobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_native_place
     *
     * @return the value of staff.staff_native_place
     *
     * @mbg.generated
     */
    public String getStaffNativePlace() {
        return staffNativePlace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_native_place
     *
     * @param staffNativePlace the value for staff.staff_native_place
     *
     * @mbg.generated
     */
    public void setStaffNativePlace(String staffNativePlace) {
        this.staffNativePlace = staffNativePlace == null ? null : staffNativePlace.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.staff_type_id
     *
     * @return the value of staff.staff_type_id
     *
     * @mbg.generated
     */
    public Integer getStaffTypeId() {
        return staffTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.staff_type_id
     *
     * @param staffTypeId the value for staff.staff_type_id
     *
     * @mbg.generated
     */
    public void setStaffTypeId(Integer staffTypeId) {
        this.staffTypeId = staffTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.is_person
     *
     * @return the value of staff.is_person
     *
     * @mbg.generated
     */
    public Integer getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Integer isPerson) {
        this.isPerson = isPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.create_time
     *
     * @return the value of staff.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.create_time
     *
     * @param createTime the value for staff.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column staff.group_id
     *
     * @return the value of staff.group_id
     *
     * @mbg.generated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column staff.group_id
     *
     * @param groupId the value for staff.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

}
