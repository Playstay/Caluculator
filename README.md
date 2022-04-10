## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# 開発用メモ
- 数字はどう保持する?
    + doubleでは小数計算時に誤差が発生してしまう。
    + しかし、BigDecimalでは割と簡単に切り捨てが発生する
        * ex.) 1/3
    + どちらを取るか
    
## プログラムの流れ
1. 入力をトークンに分解。その際に入力として受け付けないものがあったらエラー
2. トークンを順に読んで逆ポーランド記法に変換。以下の場合にはエラーとする
    1. 文法上続けて受け入れられないもの(数値の次に数値)
    2. かっこの数が一致していないもの
    3. 逆ポーランド記法に落とし込めないもの
3. 逆ポーランド記法をもとに計算を実行する。0除算の場合はエラーとする。

## 必要なクラス群
- 計算機
    + 全てのクラスを取りまとめるクラス。対外的にはこのクラスを利用して計算を行う。
- トークン分解機
    + 入力をトークンに分解し、リストに変換するクラス。ここでは特に文法が正しいかをみず、機械的に分解する。
- 文法解析機
    + トークンを順に読み込み逆ポーランド記法に落としむクラス
- 計算処機
    + 逆ポーランド記法をもとに計算を実行する
- トークン
    + 分解する際に生成するトークン。以下の情報をもつ
        * トークンの種類(数値、計算記号、カッコ)
        * 数値の場合、その値
        * そのトークンの優先度。大きい方が優先度が高い