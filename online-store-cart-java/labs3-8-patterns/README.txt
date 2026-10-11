ЛР №6-8. Структурні шаблони проєктування
Предметна область: кошик інтернет-магазину
Мова: Java 17+
Авторки: Бикова Поліна, Гудан Ванесса

Проєкт продовжує лабораторні №3-5: Singleton, Prototype, Factory Method,
Abstract Factory, Builder та Object Pool збережені.

ВІДПОВІДНІСТЬ МЕТОДИЧЦІ

- ЛР №6: Adapter і Bridge.
- ЛР №7: Composite і Flyweight.
- ЛР №8: Decorator і Proxy.
- У системі більше п'яти предметних класів; задані поля, методи та зв'язки.
- Методи структурних шаблонів реалізовані й демонструються в програмі.
- Зовнішні системи (склад, email/SMS, завантаження фото) імітуються
  консольними заглушками з назвою методу та аргументами.
- Усі класи, поля й методи документуються Javadoc.

ЛР №6: ADAPTER І BRIDGE

Adapter: shop.adapter.StockService — цільовий інтерфейс; LegacyWarehouseApi —
наявний складський API з іншими назвами методів; WarehouseAdapter перетворює
isAvailable/reserve на hasUnits/holdUnits.

Bridge: OrderNotification відокремлює тип події (OrderPlacedNotification,
OrderShippedNotification) від каналу (EmailChannel, SmsChannel), обидва
сімейства можна розширювати незалежно.

ЛР №7: COMPOSITE І FLYWEIGHT

Composite: CatalogComponent — спільна абстракція; CatalogProduct — лист дерева;
CatalogCategory — контейнер товарів або підкатегорій. Категорія показує дерево
й підраховує суму всіх вкладених товарів.

Flyweight: ProductStyle — спільний незмінний внутрішній стан; ProductStyleFactory
кешує його за категорією; ProductCard тримає індивідуальні дані товару окремо.

ЛР №8: DECORATOR І PROXY

Decorator: ProductOffer — компонент; BasicProductOffer — базовий товар;
GiftWrapDecorator та ExpressDeliveryDecorator незалежно додають послуги й ціну.
Декоратори можна комбінувати в будь-якому порядку.

Proxy: ProductImageProxy зберігає легкий посилальний об'єкт і створює
RealProductImage тільки під час першого display(). Наступні покази повторно
використовують вже завантажений об'єкт.

ЩО ПОКАЗАТИ

1. shop/StructuralPatternsDemo.java — сценарій шести нових шаблонів.
2. Для кожного шаблону відкрити пакет adapter, bridge, composite, flyweight,
   decorator або proxy та відповідні класи з опису вище.
3. shop/StructuralPatternsTest.java — автоматизовані перевірки.
4. docs/index.html — згенерована документація класів.

ЗАПУСК

  ./run.sh demo      запускає всі демонстрації ЛР №3-8
  ./run.sh test      перевіряє ЛР №5 і нові структурні шаблони
  ./run.sh javadoc   генерує HTML-документацію у docs/
  ./run.sh clean     видаляє локальні скомпільовані файли
