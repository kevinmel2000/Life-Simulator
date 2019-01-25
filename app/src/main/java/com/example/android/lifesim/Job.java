package com.example.android.lifesim;

public class Job {
    private String jobName;
    private double jobSalary;

    public Job(String jobName){
        this.jobName = jobName;

        switch (jobName){
            case "Pizza Shop Worker":
                jobSalary = 2500;
                break;
        }
    }

    // Getters
    public double getJobSalary() { return jobSalary; }
    public String getJobName() { return jobName; }

    // Setters
    public void setJobSalary(double jobSalary) { this.jobSalary = jobSalary; }
    public void setJobName(String jobName) { this.jobName = jobName; }
}
