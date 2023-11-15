package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.domain.menu.Menu;
import christmas.utils.Parse;
import java.util.Map;

public class InputView {

    private static final String INPUT_ESTIMATED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MENU_AND_QUANTITY_MESSAGE = "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int readEstimatedVisitDate() {
        System.out.println(INPUT_ESTIMATED_VISIT_DATE_MESSAGE);
        final String input = readLine();
        return Parse.parseDate(input);
    }

    public Map<Menu, Integer> readOrderMenuAndQuantity() {
        System.out.println(INPUT_ORDER_MENU_AND_QUANTITY_MESSAGE);
        final String input = readLine();
        return Parse.parseOrder(input);
    }
}
