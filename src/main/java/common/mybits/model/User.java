package common.mybits.model;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.Id
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.Id
     *
     * @return the value of user.Id
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.Id
     *
     * @param id the value for user.Id
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbggenerated Mon Oct 09 21:58:38 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }
}