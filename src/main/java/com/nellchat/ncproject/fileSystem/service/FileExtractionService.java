package com.nellchat.ncproject.fileSystem.service;

import com.nellchat.ncproject.publicChat.domain.PublicChat;
import com.nellchat.ncproject.publicChat.service.CombinePublicChatService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileExtractionService {

    private final CombinePublicChatService combinePublicChatService;

    public void ExcelExtract(Long chatRoomNum, String title){


       List<PublicChat> publicChats = combinePublicChatService.findByAllForPublicChat(chatRoomNum);

        File file = new File("C:\\Users\\Nell\\Desktop\\UploadFileRepository\\"+ title+".xlsx");
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet1");

        // 헤더 행 생성
        // 첫 번째 행 생성 (인덱스는 0부터 시작)
        Row row = sheet.createRow(0);

        // 첫 번째 열(0번 열)에 데이터 추가
        Cell cell0_0 = row.createCell(0);
        cell0_0.setCellValue("No");

        // 다음 열(1번 열)에 데이터 추가
        Cell cell0_1 = row.createCell(1);
        cell0_1.setCellValue("작성자");

        // 다음 열(2번 열)에 데이터 추가
        Cell cell0_2 = row.createCell(2);
        cell0_2.setCellValue("내용");

        // 다음 열(2번 열)에 데이터 추가
        Cell cell0_3 = row.createCell(3);
        cell0_3.setCellValue("전송 날짜");

        int rownum =1;
        Row row2;
        for(PublicChat publicChat : publicChats){
            row2 = sheet.createRow(rownum);

            // 첫 번째 열(0번 열)에 데이터 추가
            Cell cell1_0 = row2.createCell(0);
            cell1_0.setCellValue(publicChat.getId());

            // 다음 열(1번 열)에 데이터 추가
            Cell cell1_1 = row2.createCell(1);
            cell1_1.setCellValue(publicChat.getPublicChatUser().getMember().getMemberName());

            Cell cell1_2 = row2.createCell(2);
            cell1_2.setCellValue(publicChat.getMessage());

            Cell cell1_3 = row2.createCell(3);
            cell1_3.setCellValue(publicChat.getSendTime().toString());

            rownum ++;

        }



        try (
                FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            System.out.println("Excel 파일이 성공적으로 생성되었습니다!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
