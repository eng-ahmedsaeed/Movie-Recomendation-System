package ProjectTm;

import java.io.File;

public class EndToEndIntegrationTest {
    public static void main(String[] args) {
        String resultPath = "recommendation.txt";
        File oldResult = new File(resultPath);
        if(oldResult.exists()) oldResult.delete();

        System.out.println("Starting End-to-End Integration Test...");

        // 2. Execute full Main flow
        try {
            Main.main(new String[]{}); 

            // 3. Verify Output
            File resultFile = new File(resultPath);
            if (resultFile.exists() && resultFile.length() > 0) {
                System.out.println("SUCCESS: " + resultPath + " was created and is not empty.");
                System.out.println("End-to-end flow is seamless.");
            } else {
                System.err.println("FAILURE: result file was not created or is empty.");
            }

        } catch (Exception e) {
            System.err.println("SYSTEM CRASH: Integration failed at a critical junction.");
            e.printStackTrace();
        }
    }
}