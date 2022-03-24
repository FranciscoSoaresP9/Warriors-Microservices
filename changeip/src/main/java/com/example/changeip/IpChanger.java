package com.example.changeip;

import java.io.*;
import java.util.Scanner;

public class IpChanger {


    public void ChangeAllDBServicesIp() {
        System.out.println("CHANGE IP OF SERVICE WITH DB");
        Scanner scanner = new Scanner(System.in);
        String newIp = scanner.next();
        try {
            accountIp(newIp);
            resourcesIp(newIp);
            loginIp(newIp);
            pointsIp(newIp);
            statusIp(newIp);
            warriorIp(newIp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeServiceWithoutDB() {
        System.out.println("CHANGE IP OF SERVICES WITHOUT DB");
        Scanner scanner = new Scanner(System.in);
        String newIp = scanner.next();
        try {
            gateWayIp(newIp);
            loginIp(newIp);
            pageServiceIp(newIp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void resourcesIp(String newIp) throws IOException {
        File file = new File("..\\resourceService\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void accountIp(String newIp) throws IOException {
        File file = new File("..\\account\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void configIp(String newIp) throws IOException {
        File file = new File("..\\config-server\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void gateWayIp(String newIp) throws IOException {
        File file = new File("..\\gateway\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void loginIp(String newIp) throws IOException {
        File file = new File("..\\login\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void pageServiceIp(String newIp) throws IOException {
        File file = new File("..\\pagesService\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void pointsIp(String newIp) throws IOException {
        File file = new File("..\\points\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void registryIp(String newIp) throws IOException {
        File file = new File("..\\registry\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void statusIp(String newIp) throws IOException {
        File file = new File("..\\status\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void warriorIp(String newIp) throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void change(File file, String newIp) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("spring.config.import=optional:configserver:http://" + newIp + port + "/" + "\n" +
                "management.endpoints.web.exposure.include=*");
        fileWriter.flush();
        fileWriter.close();
    }

}
