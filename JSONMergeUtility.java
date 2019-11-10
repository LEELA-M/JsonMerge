package json.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMergeUtility {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter your file path: ");
		String fileInputPath = myObj.nextLine();
		JSONArray jsonOutputArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		FileWriter fw = null;
		File file = null;
		try {
			int fileCount = new File(fileInputPath).list().length;
			System.out.println("Max File Size is less then 1kb");
			for (int i = 0; i < fileCount; i++) {
				file = new File(fileInputPath + "/data" + (i + 1) + ".json");
				FileInputStream fis = new FileInputStream(file);
				jsonObj = new JSONObject(IOUtils.toString(fis, "UTF-8"));
				int length = jsonObj.getJSONArray("strikers").length();
				for (int j = 0; j < length; j++) {
					jsonOutputArray.put(new JSONObject(jsonObj.getJSONArray("strikers").get(j).toString()));
				}
			}
			if ((jsonOutputArray.toString().length() / 1024) > 0) {
				System.out.println("Merged files will never be greater than Max File Size.");
			} else {
				System.out.println("Enter your output file path: ");
				String fileOutputPath = myObj.nextLine();
				fw = new FileWriter(fileOutputPath + "/merge1.json");
				fw.write(jsonObj.put("strikers", jsonOutputArray).toString());
				System.out.println("Output file name: merge1.json");
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
