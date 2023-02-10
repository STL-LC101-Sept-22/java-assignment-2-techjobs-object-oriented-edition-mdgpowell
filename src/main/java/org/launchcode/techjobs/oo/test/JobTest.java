package org.launchcode.techjobs.oo.test;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.junit.Assert;
import org.launchcode.techjobs.oo.*;

import static org.junit.Assert.*;

/**
 * Created by LaunchCode
 */
@RunWith(JUnit4.class)
public class JobTest {
    @Test
    public void testSettingJobId() {
        Job job1 = new Job();
        Job job2 = new Job();
        assertNotEquals("the job IDs should be distinct", job1, job2);
    }
    @Test
    public void testJobConstructorSetsAllFields() {
       Job testJob = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
       assertTrue(testJob.getName() instanceof String);
       assertTrue(testJob.getEmployer() instanceof Employer);
       assertTrue(testJob.getLocation() instanceof Location);
       assertTrue(testJob.getPositionType() instanceof PositionType);
       assertTrue(testJob.getCoreCompetency() instanceof  CoreCompetency);

       assertEquals(testJob.getName(), "Product tester");
       assertEquals(testJob.getEmployer().getValue(), "ACME");
       assertEquals(testJob.getLocation().getValue(), "Desert");
       assertEquals(testJob.getPositionType().getValue(), "Quality control");
       assertEquals(testJob.getCoreCompetency().getValue(), "Persistence");
    }
    @Test
    public void testJobsForEquality() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        Job job2 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        assertFalse(job1.equals(job2));
    }
    @Test
    public void testToStringStartsAndEndsWithNewLine() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        char firstChar = job1.toString().charAt(0);
        char lastChar = job1.toString().charAt(job1.toString().length()-1);
        assertEquals(firstChar, '\n');
        assertEquals(lastChar, '\n');
    }
    @Test
    public void testToStringContainsCorrectLabelsAndData() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        String jobString = job1.toString();
        assertTrue(jobString.contains("ID:"));
        assertTrue(jobString.contains("Name:"));
        assertTrue(jobString.contains("Employer:"));
        assertTrue(jobString.contains("Location:"));
        assertTrue(jobString.contains("Position Type:"));
        assertTrue(jobString.contains("Core Competency:"));

        int idIndex = jobString.indexOf("ID: ") + 4;
        int nameIndex = jobString.indexOf("Name: ") + 6;
        int employerIndex = jobString.indexOf("Employer: ") + 10;
        int locationIndex = jobString.indexOf("Location: ") + 10;
        int positionTypeIndex = jobString.indexOf("Position Type: ") + 15;
        int coreCompetencyIndex = jobString.indexOf("Core Competency: ") +  17;
        assertEquals("Product tester", jobString.substring(nameIndex, nameIndex+14));
        assertEquals("ACME", jobString.substring(employerIndex, employerIndex+4));
        assertEquals("Desert", jobString.substring(locationIndex, locationIndex+6));
        assertEquals("Quality control", jobString.substring(positionTypeIndex, positionTypeIndex+15));
        assertEquals("Persistence", jobString.substring(coreCompetencyIndex, coreCompetencyIndex+11));

    }
    @Test
    public void testToStringHandlesEmptyField() {
        Job job1 = new Job();
        String jobString = job1.toString();
        assertTrue(jobString.contains("Data not available"));

        int nameIndex = jobString.indexOf("Name: ")+6;
        assertEquals("Data not available", jobString.substring(nameIndex, nameIndex+18));
    }
}
