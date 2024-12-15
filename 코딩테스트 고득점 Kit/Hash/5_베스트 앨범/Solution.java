import java.util.*;

class Genre implements Comparable<Genre> {

    private String name;
    private int play;

    public Genre(String name, int play) {
        this.name = name;
        this.play = play;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Genre o) {
        return o.play - this.play;
    }

}

class Music implements Comparable<Music> {

    private int idx, play;

    public Music(int idx, int play) {
        this.idx = idx;
        this.play = play;
    }

    public int getIdx() {
        return idx;
    }

    @Override
    public int compareTo(Music o) {
        return o.play - this.play;
    }

}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        List<Genre> genreList = new ArrayList<>();
        for (String genre : map.keySet()) {
            genreList.add(new Genre(genre, map.get(genre)));
        }
        Collections.sort(genreList);
        List<Music> album = new ArrayList<>();
        for (Genre genre : genreList) {
            List<Music> musicList = new ArrayList<>();
            for (int i = 0; i < genres.length; i++) {
                if (genre.getName().equals(genres[i])) {
                    musicList.add(new Music(i, plays[i]));
                }
            }
            Collections.sort(musicList);
            album.add(musicList.get(0));
            if (musicList.size() > 1) {
                album.add(musicList.get(1));
            }
        }
        int[] answer = new int[album.size()];
        for (int i = 0; i < album.size(); i++) {
            answer[i] = album.get(i).getIdx();
        }
        return answer;
    }
}
