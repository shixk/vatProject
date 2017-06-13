package com.imooc.vat.cronjob.domain;

import java.util.Date;

public class JobMonitor {
	  private Integer id;

	    private String jobname;

	    private Date strtime;

	    private Date endtime;

	    private Integer number;

	    private String bak;

	    private String baka;

	    private String bakb;

	    private String bakc;

	    private String des;
	    
	    private String IsLoseNo;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getJobname() {
	        return jobname;
	    }

	    public void setJobname(String jobname) {
	        this.jobname = jobname == null ? null : jobname.trim();
	    }

	    public Date getStrtime() {
	        return strtime;
	    }

	    public void setStrtime(Date strtime) {
	        this.strtime = strtime;
	    }

	    public Date getEndtime() {
	        return endtime;
	    }

	    public void setEndtime(Date endtime) {
	        this.endtime = endtime;
	    }

	    public Integer getNumber() {
	        return number;
	    }

	    public void setNumber(Integer number) {
	        this.number = number;
	    }

	    public String getBak() {
	        return bak;
	    }

	    public void setBak(String bak) {
	        this.bak = bak == null ? null : bak.trim();
	    }

	    public String getBaka() {
	        return baka;
	    }

	    public void setBaka(String baka) {
	        this.baka = baka == null ? null : baka.trim();
	    }

	    public String getBakb() {
	        return bakb;
	    }

	    public void setBakb(String bakb) {
	        this.bakb = bakb == null ? null : bakb.trim();
	    }

	    public String getBakc() {
	        return bakc;
	    }

	    public void setBakc(String bakc) {
	        this.bakc = bakc == null ? null : bakc.trim();
	    }

	    public String getDes() {
	        return des;
	    }

	    public void setDes(String des) {
	        this.des = des == null ? null : des.trim();
	    }

		public String getIsLoseNo() {
			return IsLoseNo;
		}

		public void setIsLoseNo(String isLoseNo) {
			IsLoseNo = isLoseNo;
		}
}
