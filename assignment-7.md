# Assignment 7 - Code Coverage and Mutation Testing

**Name:** Winston Spencer
**Class:** CSCI-713  
**Assignment:** Assignment 7: Write Effective Unit Tests
**Date:** December 7, 2025  

## GitHub Submission

**Final Commit URL:** https://github.com/winston-spencer/CSCI-713-Exercise.git

## JaCoCo Code Coverage Reports

### Line Coverage Screenshots
- ![Student Class Line Coverage](screenshot/jacoco-student-line-coverage.png)
- ![StudentService Class Line Coverage](screenshot/jacoco-studentservice-line-coverage.png)
- ![Utils Class Line Coverage](screenshot/jacoco-utils-line-coverage.png)
- ![Main Class Line Coverage](screenshot/jacoco-main-line-coverage.png)

### Branch Coverage Screenshots
- ![Student Class Branch Coverage](screenshot/jacoco-student-branch-coverage.png)
- ![StudentService Class Branch Coverage](screenshot/jacoco-studentservice-branch-coverage.png)
- ![Utils Class Branch Coverage](screenshot/jacoco-utils-branch-coverage.png)
- ![Main Class Branch Coverage](screenshot/jacoco-main-branch-coverage.png)

## PIT Mutation Testing Reports

### Mutation Coverage Screenshots
- ![Student Class Mutation Coverage](screenshot/pit-student-mutation-coverage.png)
- ![StudentService Class Mutation Coverage](screenshot/pit-studentservice-mutation-coverage.png)
- ![Utils Class Mutation Coverage](screenshot/pit-utils-mutation-coverage.png)
- ![Main Class Mutation Coverage](screenshot/pit-main-mutation-coverage.png)

## Summary

**JaCoCo Results:**
- Overall Line Coverage: 100%
- Overall Branch Coverage: [Insert percentage]

**PIT Mutation Testing Results:**
- Overall Mutation Score: 96% (24/25 mutations killed)
- Surviving Mutations: 1 (ConditionalsBoundaryMutator in Student.setAge)

## Notes

The project achieves excellent code coverage with JaCoCo showing 100% line coverage across all classes. PIT mutation testing reveals a 96% mutation score, with only one surviving mutation that appears to be equivalent (both original and mutated code produce the same observable behavior).