package site.metacoding.greenrandomrpg.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class AbuseFilter {
    public static String abuseFilter(String msg) { // 욕설 필터링.
        try {
            BufferedReader br = new BufferedReader( // 욕설 리스트파일을 버퍼에 담음.
                    new InputStreamReader(new FileInputStream("src/main/resources/static/text/abuseList.txt"),
                            "UTF-8"));
            String abuse = br.readLine(); // 버퍼에 있는것을 String에 담음
            List<String> abuseList = Arrays.asList(abuse.split(",")); // ,로 구분되어있으므로 파싱해서 List에 담음.
            for (String abuseParse : abuseList) { // 리스트 사이즈만큼 for-eache
                if (msg.contains(abuseParse)) { // 만약 욕설이 있으면
                    String Parse = "";
                    for (int i = 0; i < abuseParse.length(); i++) { // 욕설의 길이만큼 String에 *담음.
                        Parse += "*";
                    }
                    msg = msg.replaceAll(abuseParse, Parse); // 받은 메세지의 욕설부분을 *로 변환.
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("파일을 찾지 못했습니다.");
        }
        return msg; // 바뀐 메세지 반환.
    }
}
