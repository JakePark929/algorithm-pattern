package com.jake.argorithm.programmers.lv2;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [ 프렌즈4블록 ]
 *
 * 블라인드 공채를 통과한 신입 사원 라이언은 신규 게임 개발 업무를 맡게 되었다. 이번에 출시할 게임 제목은 "프렌즈4블록".
 * 같은 모양의 카카오프렌즈 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임이다.
 *
 * 만약 판이 위와 같이 주어질 경우, 라이언이 2×2로 배치된 7개 블록과 콘이 2×2로 배치된 4개 블록이 지워진다.
 * 같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.
 *
 * 블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
 *
 * 만약 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복하게 된다.
 *
 * 위 초기 배치를 문자로 표시하면 아래와 같다.
 *
 * TTTANT
 * RRFACC
 * RRRFCC
 * TRRRAA
 * TTMMMF
 * TMMTTJ
 *
 * 각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)을 의미한다
 * 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.
 *
 * 작성일 : 24.09.29
 */
public class P17679 {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];

        // 문자열 배열을 문자 배열로 변환
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            boolean[][] toBeRemoved = new boolean[m][n];
            boolean found = false;

            // 2x2 블록 찾기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] == 0) continue;  // 이미 지워진 블록이면 건너뛰기
                    char block = map[i][j];
                    if (block == map[i][j + 1] && block == map[i + 1][j] && block == map[i + 1][j + 1]) {
                        toBeRemoved[i][j] = true;
                        toBeRemoved[i][j + 1] = true;
                        toBeRemoved[i + 1][j] = true;
                        toBeRemoved[i + 1][j + 1] = true;
                        found = true;
                    }
                }
            }

            // 더 이상 지워질 블록이 없으면 종료
            if (!found) break;

            // 블록 제거
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (toBeRemoved[i][j]) {
                        map[i][j] = 0;  // 지운 블록은 0으로 표시
                        answer++;  // 지워진 블록 수 카운트
                    }
                }
            }

            // 블록을 아래로 내리기
            for (int j = 0; j < n; j++) {
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] == 0) {
                        // 현재 칸이 비어있다면, 위에서 블록을 내려옴
                        int k = i - 1;
                        while (k >= 0 && map[k][j] == 0) {
                            k--;  // 가장 가까운 블록을 찾음
                        }
                        if (k >= 0) {
                            map[i][j] = map[k][j];
                            map[k][j] = 0;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public int solution2(int m, int n, String[] board) {
        return new Board(board).play();
    }

    private static class Board {
        private final List<List<Block>> board;

        public Board(List<List<Block>> board) {
            this.board = board;
        }

        public Board(String[] board) {
            this(IntStream.range(0, board.length)
                    .mapToObj(i -> IntStream.range(0, board[i].length())
                            .mapToObj(j -> new Block(i + String.valueOf(j), String.valueOf(board[i].charAt(j))))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList())
            );
        }

        public Integer play() {
            int popCount = 0;
            Set<Block> removableBlocks;

            while ((removableBlocks = getRemovableBlocks()).size() > 1) {
                popCount += removableBlocks.size();
                removableBlocks.forEach(Block::toBlank);
                pushDown();
            }

            return popCount;
        }

        private Set<Block> getRemovableBlocks() {
            Set<Block> removableBlocks = new HashSet<>();

            for (int i = 0, height = board.size() - 1; i < height; i++) {
                for (int j = 0, width = board.get(0).size() - 1; j < width; j++) {
                    Block block = board.get(i).get(j);
                    addRemovableBlocks(removableBlocks, i, j, block);
                }
            }

            return removableBlocks;
        }

        private void addRemovableBlocks(Set<Block> removableBlocks, int i, int j, Block block) {
            if (block.isBlank()) {
                return;
            }

            Set<Block> blocks = new HashSet<>();
            blocks.add(block);

            for (int k = i; k <= i + 1; k++) {
                for (int l = j; l <= j + 1; l++) {
                    if (k == i && l == j) {
                        continue;
                    }

                    if (isInvalidPosition(k, l)) {
                        return;
                    }

                    Block currentBlock = board.get(k).get(l);

                    if (block.hasSameNameWith(currentBlock)) {
                        blocks.add(currentBlock);
                    }
                }
            }

            if (blocks.size() > 3) {
                removableBlocks.addAll(blocks);
                removableBlocks.forEach(Block::check);
            }
        }

        private void pushDown() {
            for (int j = 0, width = board.get(0).size(); j < width; j++) {
                pushDown(j);
            }
        }

        private void pushDown(int j) {
            int firstBlankIndex = getFirstBlankIndex(j);
            int firstNotBlankIndexAfterBlank = getFirstNotBlankIndexAfterBlank(j, firstBlankIndex);

            while (firstBlankIndex >= 0 && firstNotBlankIndexAfterBlank >= 0) {
                Block blank = board.get(firstBlankIndex).get(j);
                Block notBlank = board.get(firstNotBlankIndexAfterBlank).get(j);

                board.get(firstBlankIndex).set(j, notBlank);
                board.get(firstNotBlankIndexAfterBlank).set(j, blank);

                firstBlankIndex = getFirstBlankIndex(j);
                firstNotBlankIndexAfterBlank = getFirstNotBlankIndexAfterBlank(j, firstBlankIndex);
            }
        }

        private boolean isInvalidPosition(int i, int j) {
            return i >= board.size() || j >= board.get(0).size();
        }

        private int getFirstBlankIndex(int j) {
            for (int i = board.size() - 1; i >= 0; i--) {
                if (board.get(i).get(j).isBlank()) {
                    return i;
                }
            }

            return -1;
        }

        private int getFirstNotBlankIndexAfterBlank(int j, int firstBlankIndex) {
            if (firstBlankIndex < 0) {
                return -1;
            }

            for (int i = firstBlankIndex - 1; i >= 0; i--) {
                if (!board.get(i).get(j).isBlank()) {
                    return i;
                }
            }

            return -1;
        }
    }

    private static class Block {
        private static final String BLANK = "";
        private final String id;
        private String name;
        private boolean checked = false;

        public Block(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void toBlank() {
            this.name = BLANK;
        }

        public boolean isBlank() {
            return BLANK.equals(this.name);
        }

        public boolean isChecked() {
            return this.checked;
        }

        public void check() {
            this.checked = true;
        }

        public boolean hasSameNameWith(Block block) {
            return this.name.equals(block.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return Objects.equals(id, block.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        final int solution = new P17679().solution(6, 6, board);

        System.out.println(solution);
    }
}
