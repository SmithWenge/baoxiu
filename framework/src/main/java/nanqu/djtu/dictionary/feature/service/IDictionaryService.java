package nanqu.djtu.dictionary.feature.service;

import nanqu.djtu.dictionary.feature.Dictionary;

import java.util.List;

public interface IDictionaryService {
    Dictionary getDictionaryById(String id);
    List<Dictionary> getAllDictionaries();
}
