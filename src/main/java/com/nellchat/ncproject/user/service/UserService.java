package com.nellchat.ncproject.user.service;

import com.nellchat.ncproject.user.domain.User;
import com.nellchat.ncproject.user.dto.UserDTO;
import com.nellchat.ncproject.user.exception.IdDuplicationException;
import com.nellchat.ncproject.user.exception.PasswordCheckFailException;
import com.nellchat.ncproject.user.repository.UserRepository;
import com.nellchat.ncproject.user.vo.JoinResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public void updateInfo(User user){
        User findUser = userRepository.findByNumber(user.getNumber());
        findUser.updateInfo(user.getUserName(), user.getUserNickname(), user.getEmail());

    }

    public User findByNumber(Long num){
        return userRepository.findByNumber(num);

    }

    public User findById(String Id){
        return  userRepository.findById(Id);
    }

    public JoinResult save(UserDTO userDTO){
        JoinResult joinResult = JoinResult.STANDBY;
        try {
            duplicateCheckId(userDTO.getUserId());
            //아이디 중복 여부에 따라 유저 회원가입이 될지 안될지

            joinPasswordCheck(userDTO.getPasswordOne(), userDTO.getPasswordTwo());

            log.info("UserService Save : 입력받은 값 = {}", userDTO.toString());
            userRepository.save(User.createUser(userDTO.getUserId(),userDTO.getPasswordOne(),userDTO.getUserName(), userDTO.getUserNickname(), userDTO.getEmail()));
            joinResult = JoinResult.SUCCESS;
        } catch (IdDuplicationException e) {
            log.info("{}",e);
            joinResult = JoinResult.ID_EEROR;
        }catch (PasswordCheckFailException e){
            log.info("{}",e);
            joinResult=JoinResult.PASSWORD_ERROR;
        }
        return joinResult;
    }

    //아이디 중복 검증
    public void duplicateCheckId(String id) throws IdDuplicationException {
        User findUsers = null;
        try{
            findUsers = userRepository.findById(id);
        }catch (NullPointerException e){
            log.info("중복 발생 에러가 발생한 것인가");
        }finally {
            if(findUsers!=null) {
                throw new IdDuplicationException("이미 존재하는 아이디 입니다.");
            }else{
                log.info("UserService : 중복 검사 통과");
            }
        }

    }

    //회원가입시 비밀번호 이중체크
    public void joinPasswordCheck(String passwordOne , String passwordTwo) throws PasswordCheckFailException {
        if(passwordOne.equals(passwordTwo)){
            log.info("UserService : 비밀번호 체크 성공");
        }else{
            throw new PasswordCheckFailException("비밀번호가 맞지 않습니다.");
        }
    }

}
