package org.example.payload;

/**
 * Created By: Sambhav
 * Created On: 15-10-2020 || 10:43 PM
 * Project Name: jira-api-automation
 **/
public class AddCommentJSON {
    public static String getCommentInfo() {
        return "{\n" +
                "    \"body\": \"This is test comment generate by Test Automation using Rest-Assured.\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";
    }
}
