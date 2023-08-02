import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Objects;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class AptekaTest {
    @BeforeEach
    public void setBeforeEach () {

        open("https://aptekaeconom.com/");
        Selenide.webdriver().object().manage().addCookie(new Cookie("current_region", "119202"));
        Selenide.webdriver().object().navigate().refresh();

    }

    //тест может флакать из-за того что не во всех подкатегориях есть товары
    @Test
    @DisplayName("Проверка перехода в список товаров через клик по видимым кнопкам каталога")
    public void shouldGoToProductsList() {

        int randomNumber = new Random().nextInt(5);

        step("Клик на рандомную категорию в хлебных крошках", () -> {
            MainPage.OPEN_CATEGORIES.get(randomNumber).click();
        });

        step("Клик на рандомный подкаталог", () -> {
            MainPage.OPEN_SUBCATEGORIES.get(new Random().nextInt(MainPage.OPEN_SUBCATEGORIES.size())).click();
        });


        step("Проверяем что отображается хотя бы один товар", () -> {
            Assertions.assertNotEquals(MainPage.PRODUCT_LIST.size(), 0 , "Товаров нет");
        });

        step("Проверяем совпадение названий каталога и подкаталога в различных местах", () -> {
            step("Проверка названия каталога в хлебных крошках и названия каталога сверху", () -> {
                Assertions.assertEquals(MainPage.OPEN_CATEGORIES.get(randomNumber).getText(),
                        MainPage.CATALOG_ON_THE_TOP.getText(),
                        "Название каталога в хлебных крошках и название каталога сверху не совпадают");
            });
            step("Проверка названия каталога сверху и названия каталога слева", () -> {
                Assertions.assertEquals(MainPage.CATALOG_ON_THE_TOP.getText(),
                        MainPage.CATALOG_ON_THE_LEFT.get(randomNumber).getText(),
                        "Название каталога сверху и название каталога слева не совпадают");
            });
            step("Проверка названия подкаталога в заголовке и в каталоге сверху", () -> {
                Assertions.assertEquals(MainPage.SUBCATALOG_NAME.getText(),
                        MainPage.SUBCATALOG_ON_THE_TOP.getText(),
                        "Названия подкаталога в заголовке и в каталоге сверху не совпадают");
            });
        });
    }

    //тест может флакать из-за того что не во всех подкатегориях есть товары
    @Test
    @DisplayName("Проверка перехода в список товаров через выпадающее меню по видимым кнопкам каталога")
    public void shouldGoToProductsListWithDropDownMenu() {

        int randomNumber = new Random().nextInt(4) + 2;

        step("Наводим мышку на рандомную категорию в хлебных крошках", () -> {
            MainPage.getElementFromOpenCategories(randomNumber).hover().shouldBe(visible);
        });

        step("Клик на рандомную подкатегорию", () -> {
            MainPage.getElementFromOpenSubcategories(randomNumber)
                    .get(new Random().nextInt(MainPage.getElementFromOpenSubcategories(randomNumber).size())).click();
        });

        step("Проверяем что отображается хотя бы один товар", () -> {
            Assertions.assertNotEquals(MainPage.PRODUCT_LIST.size(), 0 , "Товаров нет");
        });

        step("Проверяем совпадение названий каталога и подкаталога в различных местах", () -> {
            step("Проверка названия каталога в хлебных крошках и названия каталога сверху", () -> {
                Assertions.assertEquals(MainPage.OPEN_CATEGORIES.get(randomNumber - 2).getText(),
                        MainPage.CATALOG_ON_THE_TOP.getText(),
                        "Название каталога в хлебных крошках и название каталога сверху не совпадают");
            });
            step("Проверка названия каталога сверху и названия каталога слева", () -> {
                Assertions.assertEquals(MainPage.CATALOG_ON_THE_TOP.getText(),
                        MainPage.CATALOG_ON_THE_LEFT.get(randomNumber - 2).getText(),
                        "Название каталога сверху и название каталога слева не совпадают");
            });
            step("Проверка названия подкаталога в заголовке и в каталоге сверху", () -> {
                Assertions.assertEquals(MainPage.SUBCATALOG_NAME.getText(),
                        MainPage.SUBCATALOG_ON_THE_TOP.getText(),
                        "Названия подкаталога в заголовке и в каталоге сверху не совпадают");
            });
        });
    }

    //тест может флакать из-за того что не во всех подкатегориях есть товары
    @Test
    @DisplayName("Проверка перехода в список товаров через скрытое выпадающее меню")
    public void shouldGoToProductsListWithHiddenDropDownMenu() {

        int randomNumber = new Random().nextInt(3) + 1;

        step("Вызов скрытого меню категорий", () -> {
            MainPage.HIDDEN_MENU_OF_CATEGORIES.hover().shouldBe(visible);
        });

        step("Выбор рандомной скрытой категории", () -> {
            MainPage.getElementFromHiddenCategories(randomNumber).hover().shouldBe(visible);
        });

        step("Клик на рандомный подкаталог", () -> {
            MainPage.getElementFromHiddenSubcategories(randomNumber).
                    get(new Random().nextInt(MainPage.getElementFromHiddenSubcategories(randomNumber).size())).click();
        });

        step("Проверяем что отображается хотя бы один товар", () -> {
            Assertions.assertNotEquals(MainPage.PRODUCT_LIST.size(), 0 , "Товаров нет");
        });

        step("Проверяем совпадение названий каталога и подкаталога в различных местах", () -> {
            step("Проверка названия каталога сверху и названия каталога слева", () -> {
                Assertions.assertEquals(MainPage.CATALOG_ON_THE_TOP.getText(),
                        MainPage.CATALOG_ON_THE_LEFT.get(randomNumber + 4).getText(),
                        "Названия каталога сверху и каталога слева не совпадают");
            });
            step("Проверка названия подкаталога в заголовке и в каталоге сверху", () -> {
                Assertions.assertEquals(MainPage.SUBCATALOG_NAME.getText(),
                        MainPage.SUBCATALOG_ON_THE_TOP.getText(),
                        "Названия подкаталога в заголовке и в каталоге сверху не совпадают");
            });
        });
    }

    //тесты, где результатов поиска больше 5, всегда проваливается, т.к. в пагинации есть баг
    @ParameterizedTest
    @ValueSource(strings = {"Витамин", "БИНТ", "вата", "аСпИрИн", "Фастум гель 2,5% 30г NB"})
    @DisplayName("Проверка пагинации при поиске товара, количество которого больше 5")
    public void shouldCheckSearchWithPagination(String product) {

        step("Поиск товара", () -> {
            MainPage.SEARCH.sendKeys(product);
            MainPage.SEARCH_BUTTON.click();
        });

        step("Проверяем, что если видна пагинация, то на странице 5 результатов поиска", () -> {
            if (MainPage.PAGINATION.isDisplayed()) {
                Assertions.assertEquals(MainPage.PRODUCT_LIST.size(), 5,
                        "Колличество отображаемых результатов поиска не равно 5");
            } else {
                Assertions.assertTrue(MainPage.PRODUCT_LIST.size() <= 5,
                        "Колличество отображаемых результатов поиска больше 5");
            }
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"Несуществующий препарат", "Уверен что такого препарата нет", "И этого тоже"})
    @DisplayName("Проверка поиска товара которого нет")
    public void shouldCheckSearchNegative(String product) {

        step("Поиск несуществующего товара", () -> {
            MainPage.SEARCH.sendKeys(product);
            MainPage.SEARCH_BUTTON.click();
        });

        step("Проверка, что список товаров пуст", () -> {
            if (MainPage.NOT_FOUND.isDisplayed()) {
                Assertions.assertEquals(MainPage.PRODUCT_LIST.size(), 0,
                        "В результатах поиска есть товары");
            }
        });
    }

    @Test
    @DisplayName("Переход в корзину отложенных")
    public void shouldGoToBasketOfDeferred () {

        int randomNumber = new Random().nextInt(5);

        step("Переходим в список товаров", () -> {
            MainPage.OPEN_CATEGORIES.get(randomNumber).click();
            MainPage.OPEN_SUBCATEGORIES.get(new Random().nextInt(MainPage.OPEN_SUBCATEGORIES.size())).click();
        });

        step("Проверяем что отображается хотя бы один товар", () -> {
            Assertions.assertNotEquals(MainPage.PRODUCT_LIST.size(), 0 , "Товаров нет");
        });

        step("Добавляем товар в отложенные", () -> {
            MainPage.POSTPONE_FIRST_PRODUCT.click();
        });

        step("Переходим в корзину", () -> {
            MainPage.BASKET_OF_DEFERRED.click();
        });

        sleep(1000);

        step("Проверяем, что в списке отложенных товаров есть товары", () -> {
            Assertions.assertNotEquals(MainPage.NAME_OF_PRODUCT_IN_BASKET_OF_DEFERRED.size(), 0,
                    "В корзине нет добавленных товаров");
        });

        step("Очищаем корзину", () -> {
            MainPage.CLEAN_BASKET_OF_DEFERRED.click();
        });

        sleep(1000);

        step("Проверяем, что список отложенных товаров очистился", () -> {
            Assertions.assertEquals(MainPage.NAME_OF_PRODUCT_IN_BASKET_OF_DEFERRED.size(), 0,
                    "В корзине остались товары");
        });
    }

    //тест может флакать из-за того что не во всех подкатегориях есть товары
    @Test
    @DisplayName("Проверка работа добавления в отложенные")
    public void shouldPostponeGoods () {

        int randomNumber = new Random().nextInt(5);

        step("Поверяем что изначальное значение количества товаров в сравнении равно 0", () -> {
            Assertions.assertEquals(Integer.decode(Wait().until(ExpectedConditions.visibilityOf(MainPage.NUMBER_OF_DEFERRED))
                    .getText()), 0);
        });

        step("Переходим в список товаров", () -> {
            MainPage.OPEN_CATEGORIES.get(randomNumber).click();
            MainPage.OPEN_SUBCATEGORIES.get(new Random().nextInt(MainPage.OPEN_SUBCATEGORIES.size())).click();
        });

        step("Проверяем что отображается хотя бы один товар", () -> {
            Assertions.assertNotEquals(MainPage.PRODUCT_LIST.size(), 0 , "Товаров нет");
        });

        step("Проверяем всплывающие подсказки при наведении на кнопку добавления в список отложенных товаров", () -> {
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(0).getAttribute("title"), "Отложить");
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(1).getAttribute("title"), "В отложенных");
        });

        step("Проверяем всплывающую подсказку при наведении на корзину отложенных товаров (пустую)", () -> {
            Assertions.assertTrue(Objects.requireNonNull(MainPage.BASKET_OF_DEFERRED.getAttribute("title"))
                    .contains("Список отложенных товаров пуст"),
                    "Нет надписи 'Список отложенных товаров пустСписок отложенных товаров пуст'");
        });

        step("Кликаем у первого товара на добавление в список отложенных", () -> {
            MainPage.POSTPONE_FIRST_PRODUCT.click();
        });

        sleep(1000);

        step("Проверяем  что значение количества товаров в сравнении стало 1", () -> {
            Assertions.assertEquals(Wait().until(ExpectedConditions.visibilityOf(MainPage.NUMBER_OF_DEFERRED))
                    .getText(), "1", "Значение количества товаров в сравнении не стало 1");
        });

        step("Проверяем смену класса кнопки (нажатой)", () -> {
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(0)
                    .getAttribute("class"), "wish_item to added");
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(1)
                    .getAttribute("class"), "wish_item in added");
        });

        step("Проверяем всплывающую подсказку при наведении на корзину отложенных товаров (не пустую)", () -> {
            Assertions.assertTrue(Objects.requireNonNull(MainPage.BASKET_OF_DEFERRED.getAttribute("title"))
                    .contains("В отложенных товаров на"), "Нет надписи 'В отложенных товаров на'");
        });

        sleep(1000);

        step("Проверяем, что корзина при этом пуста", () -> {
            Assertions.assertEquals(MainPage.BASKET.getAttribute("title"), "Корзина пуста");
        });

        step("Кликаем у первого товара на добавление в список отложенных", () -> {
            MainPage.POSTPONE_FIRST_PRODUCT.click();
        });

        step("проверяем смену класса кнопки (отжатой)", () -> {
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(0).getAttribute("class"), "wish_item to");
            Assertions.assertEquals(MainPage.POSTPONE_FIRST_PRODUCT_LIST.get(1).getAttribute("class"), "wish_item in");
        });

        step("Проверяем, что значение количества товаров в сравнении вернулось на 0", () -> {
            Assertions.assertEquals(Wait().until(ExpectedConditions.visibilityOf(MainPage.NUMBER_OF_DEFERRED))
                    .getText(), "0", "Значение количества товаров не вернулось на 0");
        });
    }
}
