package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class board {
    int WRONG_NUM = -1;
    int id = 4;
    Scanner scan = new Scanner(System.in);
    ArrayList<Article> articleList = new ArrayList<>();

    void run() {
        testData();
        while (true) {
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.contains("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            switch (cmd) {
                case "add" -> add();
                case "list" -> list();
                case "update" -> update();
                case "delete" -> delete();
                case "detail" -> detail();
                case "search" -> search();
                default -> System.out.println("올바른 명령어를 입력하세요.\n ( add / list / update / delete / detail / search )");
            }
        }
    }
    Article findArticleById(int inputId) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == inputId) {
                return article;
            }
        }
        return null;
    }
    String  dateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return now.format(formatter);
    }
    void testData() {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.","시녕",dateTime(), 0);
        Article a2 = new Article(2, "자바 질문좀 할게요~","시녕",dateTime(), 0);
        Article a3 = new Article(3, "정처기 따야되나요?","시녕",dateTime(), 0);

        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }
    void searchList(String text) {
        boolean aa = false;
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getTitle().contains(text)) {
                aa = true;
                System.out.printf("번호 : %d\n", article.getId());
                System.out.printf("제목 : %s\n", article.getTitle());
                System.out.println("==================");
            }
        }
        if (!aa) {
            System.out.println("검색 결과가 없습니다");
            System.out.println("==================");
        }
    }
    void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = scan.nextLine();
        System.out.println("게시물이 등록되었습니다.");
        Article article = new Article(id, title, content, dateTime(), 0);
        articleList.add(article);
        id++;
    }
    void list() {
        System.out.println("==================");
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            System.out.printf("번호 : %d\n", article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.println("==================");
        }
    }
    void update() {
        System.out.print("수정할 게시물 번호 : ");
        int updateId = intStringParse(scan.nextLine(), WRONG_NUM);
        if (updateId == WRONG_NUM) {
            return;
        }
        Article article = findArticleById(updateId);
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
        }
        else {
            System.out.print("제목 : ");
            String updateTitle = scan.nextLine();
            System.out.print("내용 : ");
            String updateContent = scan.nextLine();
            System.out.printf("%s번 게시물이 수정되었습니다.\n", updateId);

            article.setTitle(updateTitle);
            article.setContent(updateContent);
        }
    }
    void delete() {
        System.out.print("삭제할 게시물 번호 : ");
        int deleteId = intStringParse(scan.nextLine(), WRONG_NUM);
        if (deleteId == WRONG_NUM) {
            return;
        }
        Article article = findArticleById(deleteId);
        if (article == null) {
            System.out.println("없는 게시물 번호입니다.");
        }
        else {
            System.out.printf("%s번 게시물이 삭제되었습니다.\n", deleteId);
            articleList.remove(article);
        }
    }
    void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int detailId = intStringParse(scan.nextLine(), WRONG_NUM);
        if (detailId == WRONG_NUM) {
            return;
        }
        Article article = findArticleById(detailId);
        if (article == null) {
            System.out.println("존재하지 않는 게시물 번호입니다.");
        }
        else {
            article.setHit(article.getHit()+1);
            System.out.println("==================");
            System.out.printf("번호 : %d\n", article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.printf("내용 : %s\n", article.getContent());
            System.out.printf("등록날짜 : %s\n", article.getDateTime());
            System.out.printf("조회수 : %d\n", article.getHit());
            System.out.println("==================");
        }
    }
    void search() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String text = scan.nextLine();
        System.out.println("==================");
        searchList(text);
    }
    int intStringParse(String num, int WRONG_NUM) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return WRONG_NUM;
        }
    }
}
