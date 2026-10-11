# Кошик інтернет-магазину — ЛР №1 GRASP і ЛР №2 SOLID

Авторки: **Бикова Поліна та Гудан Ванесса**.

У репозиторії виконано дві лабораторні роботи з принципів проєктування ПЗ
мовою Java в одній предметній області — кошик інтернет-магазину.

## Готова версія двох лабораторних

Повний проєкт розміщено в каталозі
[`online-store-cart-java/`](online-store-cart-java/).

- [Звіт до ЛР №1 — GRASP](online-store-cart-java/reports/LR1_GRASP_Online_Store_Cart.docx)
- [Звіт до ЛР №2 — SOLID](online-store-cart-java/reports/LR2_SOLID_Online_Store_Cart.docx)
- [Матеріал для захисту](online-store-cart-java/reports/Defense_Guide_GRASP_SOLID.docx)
- [Опис реалізації GRASP](online-store-cart-java/docs/REPORT_LAB1_GRASP.md)
- [Опис реалізації SOLID](online-store-cart-java/docs/REPORT_LAB2_SOLID.md)
- [Згенерована Javadoc](online-store-cart-java/docs/javadoc/index.html)

ЛР №1 демонструє всі 9 принципів GRASP: Information Expert, Creator,
Controller, Low Coupling, High Cohesion, Polymorphism, Pure Fabrication,
Indirection і Protected Variations.

ЛР №2 демонструє всі 5 принципів SOLID: SRP, OCP, LSP, ISP і DIP.

У системі є 8 предметних класів, наслідування, агрегація та асоціація.
Зовнішні операції оплати, збереження й надсилання чека реалізовано
консольними заглушками згідно з вимогами методичних вказівок.

## Запуск

Потрібен JDK 17 або новіший:

```bash
cd online-store-cart-java
./run.sh lab1
./run.sh lab2
./run.sh test
./run.sh javadoc
```

Попередню реалізацію ЛР №1 залишено в каталогах `src/` і `docs/`, щоб не
видаляти історичні файли репозиторію.

## Продовження: лабораторні №3–8

Проєкт з породжувальними та структурними шаблонами знаходиться у
[`online-store-cart-java/labs3-8-patterns/`](online-store-cart-java/labs3-8-patterns/).
Він містить Singleton, Prototype, Factory Method, Abstract Factory, Builder,
Object Pool, Adapter, Bridge, Composite, Flyweight, Decorator і Proxy.

Для демонстрації й перевірки перейдіть до цієї папки та виконайте
`./run.sh demo` або `./run.sh test`. Javadoc розміщено в
[`docs/index.html`](online-store-cart-java/labs3-8-patterns/docs/index.html).
