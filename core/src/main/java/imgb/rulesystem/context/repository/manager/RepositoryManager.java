package imgb.rulesystem.context.repository.manager;


import imgb.rulesystem.context.repository.Repository;
import imgb.rulesystem.context.entity.Entity;

import java.util.Map;

/**
 * Created by ca on 10/13/14.<br>
 */
public class RepositoryManager {

    private Map<Class, Repository> repositoryMap;
    private Repository defaultRepository;

    public RepositoryManager(Map<Class, Repository> repositoryMap, Repository defaultRepository) {
        this.repositoryMap = repositoryMap;
        this.defaultRepository = defaultRepository;
    }

    public Repository getRepository(Entity entity) {
        if(entity == null || repositoryMap == null) {
            return null;
        }
        Repository returnRepository = null;
        RepositoryAnnotation repositoryAnnotation = entity.getClass().getAnnotation(RepositoryAnnotation.class);

        if(repositoryAnnotation != null) {
            returnRepository =  repositoryMap.get(repositoryAnnotation.value());
        }

        if(returnRepository == null) {
            returnRepository = defaultRepository;
        }

        return returnRepository;
    }

    public Repository getRepository(Class repositoryClass) {
        if(repositoryClass == null) {
            return null;
        }
        return repositoryMap.get(repositoryClass);
    }

}
