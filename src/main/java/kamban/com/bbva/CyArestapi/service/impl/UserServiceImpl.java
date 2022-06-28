package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.model.MDLUser;
import kamban.com.bbva.CyArestapi.repository.dao.DAOUser;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.UserService;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kamban.com.bbva.CyArestapi.utils.UTLConstants.CODE_DOCUMENT_USER;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DAOUser _daoUser;

    private UTLGeneralService _utlGeneralService;

    public UserServiceImpl(){
        this._utlGeneralService=new UTLGeneralService();
    }

    @Override
    public String createUser(MDLUser userData) {
        String dataReturn=new String();

        ENTEvidence<MDLUser> dataToSave=this._utlGeneralService.createModelEvidence(userData);
        dataToSave.setEvidenceTypeId(CODE_DOCUMENT_USER);

        ENTEvidence<MDLUser> dataSaved=_daoUser.save(dataToSave);

        if(dataSaved!=null){
            dataReturn= dataSaved.getId();
        }
        return dataReturn;
    }

    @Override
    public String alterUser(String userId,MDLUser userData) {
        String dataReturn=new String();

//        ENTEvidence<MDLDisciplina> dataToSave=this._utlGeneralService.createModelEvidence(userData);
//        dataToSave.setEvidenceTypeId(CODE_DOCUMENT_USER);
//
//        ENTEvidence<MDLDisciplina> dataSaved=_daoUser.save(dataToSave);
//
//        if(dataSaved!=null){
//            dataReturn= dataSaved.getId();
//        }
        return dataReturn;
    }

    @Override
    public List<MDLUser> retrieveAllUsers() {
        List<MDLUser> dataReturn =new ArrayList<>();
        List<ENTEvidence<MDLUser>> listUsers= _daoUser.findByEvidenceTypeId(CODE_DOCUMENT_USER);

        if(listUsers!=null && listUsers.size()>0){
            for (ENTEvidence<MDLUser> entUser:listUsers) {
                if(entUser.getSpecificFieldsDes()!=null){
                    entUser.getSpecificFieldsDes().setId(entUser.getId());
                    dataReturn.add(entUser.getSpecificFieldsDes());
                }
            }
        }

        return dataReturn;
    }

    @Override
    public MDLUser retrieveUserByNetworkCode(String networkCode) {
        ENTEvidence<MDLUser> evidence= _daoUser.findByNetworkCode(CODE_DOCUMENT_USER,networkCode);

        if(evidence!=null && evidence.getSpecificFieldsDes()!=null){
            evidence.getSpecificFieldsDes().setId(evidence.getId());
            return evidence.getSpecificFieldsDes();
        }else {
            return null;
        }
    }

    @Override
    public boolean existUser(String networkCode){
        ENTEvidence<MDLUser> evidence= _daoUser.findByNetworkCode(CODE_DOCUMENT_USER,networkCode);

        if(evidence!=null && evidence.getSpecificFieldsDes()!=null){
            return true;
        }else{
            return false;
        }
    }


//    private UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User getUser(String userId) {
//        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
//    }
//
//    @Override
//    public User createUser(User objUser) {
//        return userRepository.save(objUser);
//    }
//
//    @Override
//    public User updateUser(String userId, User objUser) {
//        User userFromDB = userRepository.findById(userId).orElseThrow(RuntimeException::new);
//        userFromDB.setNombre(objUser.getNombre());
//        userFromDB.setApPaterno(objUser.getApPaterno());
//        userFromDB.setApMaterno(objUser.getApMaterno());
//        userFromDB.setUsuarioCorto(objUser.getUsuarioCorto());
//        userFromDB.setEmail(objUser.getEmail());
//        userFromDB.setPorcentajeAsignacion(objUser.getPorcentajeAsignacion());
//        userFromDB.setPorcentajeDisponible(objUser.getPorcentajeDisponible());
//        userFromDB.setTechReview(objUser.isTechReview());
//        return userRepository.save(userFromDB);
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        User userFromDB = userRepository.findById(userId).orElseThrow(RuntimeException::new);
//        userRepository.delete(userFromDB);
//    }
}
