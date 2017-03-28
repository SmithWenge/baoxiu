package nanqu.djtu.dictionary.feature.service.impl;

import nanqu.djtu.dictionary.feature.Dictionary;
import nanqu.djtu.dictionary.feature.repository.IDictionaryRepository;
import nanqu.djtu.dictionary.feature.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private IDictionaryRepository repository;

    @Override
    public Dictionary getDictionaryById(String id) {
        return repository.selectById(id);
    }

    @Override
    public List<Dictionary> getAllDictionaries() {
        return repository.selectAll();
    }
}
