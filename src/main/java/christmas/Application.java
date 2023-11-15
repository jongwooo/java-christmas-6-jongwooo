package christmas;

import christmas.controller.PromotionController;

public class Application {
    public static void main(String[] args) {
        final PromotionController promotionController = new PromotionController();
        promotionController.run();
    }
}
