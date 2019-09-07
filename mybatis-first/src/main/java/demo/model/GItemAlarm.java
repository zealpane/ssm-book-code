package demo.model;

import java.io.Serializable;

public class GItemAlarm implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_item_alarm.id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_item_alarm.type
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_item_alarm.val
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    private Double val;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_item_alarm.device_id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    private Integer deviceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table g_item_alarm
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_item_alarm.id
     *
     * @return the value of g_item_alarm.id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_item_alarm.id
     *
     * @param id the value for g_item_alarm.id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_item_alarm.type
     *
     * @return the value of g_item_alarm.type
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_item_alarm.type
     *
     * @param type the value for g_item_alarm.type
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_item_alarm.val
     *
     * @return the value of g_item_alarm.val
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public Double getVal() {
        return val;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_item_alarm.val
     *
     * @param val the value for g_item_alarm.val
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public void setVal(Double val) {
        this.val = val;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_item_alarm.device_id
     *
     * @return the value of g_item_alarm.device_id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_item_alarm.device_id
     *
     * @param deviceId the value for g_item_alarm.device_id
     *
     * @mbg.generated Sat Sep 07 15:55:48 CST 2019
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}