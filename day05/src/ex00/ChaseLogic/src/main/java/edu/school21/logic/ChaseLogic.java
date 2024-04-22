package edu.school21.logic;

import java.util.SortedSet;
import java.util.TreeSet;

public class ChaseLogic {
    private final int[] map;
    private final int size;

    public ChaseLogic(int[] map, int size) {
        this.map = map;
        this.size = size;
    }

    public int getMove(int start, int end) {
        SortedSet<Path> set = new TreeSet<>();
        int[] array = map.clone();
        set.add(new Path(0, -1, start));
        Path jorney;
        while (!set.isEmpty()) {
            jorney = set.first();
            if (jorney.index == end) {
                map[start] = 0;
                map[jorney.indexFirstMove] = -1;
                return jorney.indexFirstMove;
            }
            int i = jorney.index + 1;
            if (i % size != 0) {
                if (jorney.length + 1 < array[i] || array[i] == 0 || (array[i] == -1 && jorney.indexFirstMove != -1)) {
                    set.add(new Path(jorney.length + 1, (jorney.indexFirstMove == -1) ? i : jorney.indexFirstMove, i));
                    array[i] = jorney.length + 1;
                }
            }
            i = jorney.index - 1;
            if ((i + 1) % size != 0) {
                if (jorney.length + 1 < array[i] || array[i] == 0 || (array[i] == -1 && jorney.indexFirstMove != -1)) {
                    set.add(new Path(jorney.length + 1, (jorney.indexFirstMove == -1) ? i : jorney.indexFirstMove, i));
                    array[i] = jorney.length + 1;
                }
            }
            i = jorney.index + size;
            if (i < size * size) {
                if (jorney.length + 1 < array[i] || array[i] == 0 || (array[i] == -1 && jorney.indexFirstMove != -1)) {
                    set.add(new Path(jorney.length + 1, (jorney.indexFirstMove == -1) ? i : jorney.indexFirstMove, i));
                    array[i] = jorney.length + 1;
                }
            }
            i = jorney.index - size;
            if (i >= 0) {
                if (jorney.length + 1 < array[i] || array[i] == 0 || (array[i] == -1 && jorney.indexFirstMove != -1)) {
                    set.add(new Path(jorney.length + 1, (jorney.indexFirstMove == -1) ? i : jorney.indexFirstMove, i));
                    array[i] = jorney.length + 1;
                }
            }
            if (set.size() == 1 && jorney.indexFirstMove != -1) {
                return jorney.indexFirstMove;
            }
            set.remove(set.first());
        }
        return start;
    }

    private static class Path implements Comparable<Path> {
        int length;
        int indexFirstMove;
        int index;

        public Path(int length, int indexFirstMove, int index) {
            this.length = length;
            this.indexFirstMove = indexFirstMove;
            this.index = index;
        }

        @Override
        public int compareTo(Path o) {
            if (length == o.length && index != o.index) {
                return (Math.random() > 0.5) ? 1 : -1;
            }
            return length - o.length;
        }
    }
}
