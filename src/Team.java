import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Team {
    double TeamPF, TeamPA, TeamYPG, TeamYAPG, TeamSOS;
    int numGames = 13;

    public Team(String statsPath, String sosPath, String team) {
        TeamPF = getValue(3, 1, statsPath)/numGames;
        TeamPA = getValue(4, 1, statsPath)/numGames;
        TeamYPG = getValue(3, 2, statsPath)/numGames;
        TeamYAPG = getValue(4, 2, statsPath)/numGames;
        TeamSOS = getSOS(sosPath, team);
    }

    public Double getValue( int row, int column, String path ) {
        Double value;
        Workbook wb = null;
        try {
            FileInputStream file = new FileInputStream(path);
            wb = new XSSFWorkbook(file);
        } catch(IOException e) { e.printStackTrace(); }
        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);
        Row r = sheet.getRow(row);
        Cell cell = r.getCell(column);
        value = cell.getNumericCellValue();
        return value;
    }

    public Double getSOS( String path, String team ) {
        Workbook wb = null;
        Double sos = 0.0;
        try {
            FileInputStream file = new FileInputStream(path);
            wb = new XSSFWorkbook(file);
        } catch(IOException e) { e.printStackTrace(); }
        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);
        for( int i = 1; i <= sheet.getLastRowNum(); i++ ) {
            Row r = sheet.getRow(i);
            Cell cell = r.getCell(0);
            if (team.equals(cell.getStringCellValue())) {
                sos = r.getCell(1).getNumericCellValue();
            }
        }
        return sos;
    }
}
