package nanqu.djtu.dictionary.feature.repository;

import nanqu.djtu.dictionary.feature.Dictionary;

import java.util.List;

public interface IDictionaryRepository {
    Dictionary selectById(String id);
    List<Dictionary> selectAll();
}
