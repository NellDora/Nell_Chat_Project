package com.nellchat.ncproject.publicChat.controller;


import com.nellchat.ncproject.login.loginConst.LoginSession;
import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;
import com.nellchat.ncproject.publicChat.dto.PublicChatRoomDTO;
import com.nellchat.ncproject.publicChat.service.CombinePublicChatService;
import com.nellchat.ncproject.publicChat.vo.JoinState;
import com.nellchat.ncproject.user.domain.User;
import com.nellchat.ncproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/publicChat")
@Slf4j
public class PublicChatController {


    private final CombinePublicChatService combinePublicChatService;
    private final UserService userService;

    @GetMapping("/main")
    public String chatList(Model model, @SessionAttribute(LoginSession.Login_User) Long userNum){

        User findUser = userService.findByNumber(userNum);

        List<PublicChatRoom> chatRoomList = combinePublicChatService.findByAllWithPagingForPublicChatRoom(0,10);
        List<PublicChatRoomDTO> chatRoomDTOS = new ArrayList<>();
        for(PublicChatRoom publicChatRoom : chatRoomList){
            PublicChatRoomDTO chatRoomDTO = new PublicChatRoomDTO();
            chatRoomDTO.setId(publicChatRoom.getId());
            chatRoomDTO.setRoomCode(publicChatRoom.getRoomCode());
            chatRoomDTO.setRoomName(publicChatRoom.getRoomName());
            chatRoomDTO.setRoomType(publicChatRoom.getRoomType());
            chatRoomDTO.setMasterId(publicChatRoom.getMaster().getUserId());
            chatRoomDTO.setMasterName(publicChatRoom.getMaster().getUserName());
            chatRoomDTO.setMasterNickName(publicChatRoom.getMaster().getUserNickname());
            chatRoomDTO.setCreateDate(publicChatRoom.getCreateDate());
            chatRoomDTO.setNumberOfPerson(publicChatRoom.getChatUserList().size());


            if(combinePublicChatService.findByUserAndPublicChatRoomForPublicChatUser(findUser,publicChatRoom)==null){
                chatRoomDTO.setJoinState(JoinState.NO);
            }else{
                chatRoomDTO.setJoinState(JoinState.YES);
            }




            chatRoomDTOS.add(chatRoomDTO);
        }

        model.addAttribute("chatroomList", chatRoomDTOS);
        return "/publicchat/chatroomlist";
    }

    @GetMapping("/roomCode={roomCode}")
    public String publicChatRoom(@PathVariable("roomCode") String roomCode, Model model,
                                 @SessionAttribute(LoginSession.Login_User) Long userNum, HttpServletResponse response){
        log.info("받아온 roomCode는 : {}",roomCode);
        PublicChatRoom findPublicChatRoom =
                combinePublicChatService.findByRoomCodeForPublicChatRoom(roomCode);

        User findUser = userService.findByNumber(userNum);


        try {
            if(combinePublicChatService.findByUserAndPublicChatRoomForPublicChatUser(findUser, findPublicChatRoom)==null) {
                response.setContentType("text/html;  charset=UTF-8");
                String message = "<script>alert('허가되지 않은 채팅방 유저입니다.')</script>";
                message += "<script>history.back()</script>";
                PrintWriter writer = response.getWriter();
                writer.print(message);
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        model.addAttribute("roomCode" , roomCode);
        model.addAttribute("userNum", userNum);
        return "/publicchat/public_chatroom";
    }

    @GetMapping("/createChatRoom")
    public String createChatRoom(Model model){
        model.addAttribute("publicChatRoomDTO", new PublicChatRoomDTO());
        return "/publicchat/create_public_chatroom";
    }

    @PostMapping("/createChatRoom")
    public String createChatRoom(@ModelAttribute("publicChatRoomDTO") PublicChatRoomDTO publicChatRoomDTO , @SessionAttribute(LoginSession.Login_User) Long userNum){

        User findUser = userService.findByNumber(userNum);

        PublicChatRoom publicChatRoom =
                PublicChatRoom.createPublicChatRoom(publicChatRoomDTO.getRoomName(),
                        publicChatRoomDTO.getDescription(),findUser, publicChatRoomDTO.getRoomType(), publicChatRoomDTO.getPassword());

        combinePublicChatService.saveForPublicChatRoom(publicChatRoom);

        return "redirect:/publicChat/main";
    }

    @GetMapping("/info/{chatRoomId}")
    public String chatRoomInfo(@PathVariable("chatRoomId")Long id, Model model){
        PublicChatRoom publicChatRoom = combinePublicChatService.findByIdForPublicChatRoom(id);
        log.info("호우호우호우 : {}", publicChatRoom.getRoomCode());
        PublicChatRoomDTO publicChatRoomDTO = new PublicChatRoomDTO();
        publicChatRoomDTO.setId(publicChatRoom.getId());
        publicChatRoomDTO.setRoomCode(publicChatRoom.getRoomCode());
        publicChatRoomDTO.setRoomName(publicChatRoom.getRoomName());
        publicChatRoomDTO.setRoomType(publicChatRoom.getRoomType());
        publicChatRoomDTO.setMasterId(publicChatRoom.getMaster().getUserId());
        publicChatRoomDTO.setMasterName(publicChatRoom.getMaster().getUserName());
        publicChatRoomDTO.setMasterNickName(publicChatRoom.getMaster().getUserNickname());
        publicChatRoomDTO.setCreateDate(publicChatRoom.getCreateDate());
        publicChatRoomDTO.setNumberOfPerson(publicChatRoom.getChatUserList().size());
        publicChatRoomDTO.setDescription(publicChatRoom.getDescription());

        model.addAttribute("publicChatRoomDTO", publicChatRoomDTO);

        return "/publicchat/public_chatroom_info";
    }


    @GetMapping("/joinChatRoom/{chatRoomId}")
    public String joinChatRoomGET(@PathVariable("chatRoomId")Long id, Model model){

        PublicChatRoom publicChatRoom = combinePublicChatService.findByIdForPublicChatRoom(id);
        log.info("호우호우호우 : {}", publicChatRoom.getRoomCode());
        PublicChatRoomDTO publicChatRoomDTO = new PublicChatRoomDTO();
        publicChatRoomDTO.setId(publicChatRoom.getId());
        publicChatRoomDTO.setRoomCode(publicChatRoom.getRoomCode());
        publicChatRoomDTO.setRoomName(publicChatRoom.getRoomName());
        publicChatRoomDTO.setRoomType(publicChatRoom.getRoomType());
        publicChatRoomDTO.setMasterId(publicChatRoom.getMaster().getUserId());
        publicChatRoomDTO.setMasterName(publicChatRoom.getMaster().getUserName());
        publicChatRoomDTO.setMasterNickName(publicChatRoom.getMaster().getUserNickname());
        publicChatRoomDTO.setCreateDate(publicChatRoom.getCreateDate());
        publicChatRoomDTO.setNumberOfPerson(publicChatRoom.getChatUserList().size());
        publicChatRoomDTO.setDescription(publicChatRoom.getDescription());

        model.addAttribute("publicChatRoomDTO", publicChatRoomDTO);

        return "/publicchat/join_public_chatroom";
    }

    @PostMapping("/joinChatRoom/ext")
    public String joinChatRoomPOST(@RequestParam("roomCode") String roomCode, @SessionAttribute(LoginSession.Login_User) Long userNum){


        User findUser = userService.findByNumber(userNum);
        PublicChatRoom findPublicChatRoom =  combinePublicChatService.findByRoomCodeForPublicChatRoom(roomCode);

        //채팅방 유저 생성
        PublicChatUser publicChatUser = PublicChatUser.createPublicChatUser(findPublicChatRoom, findUser);
        //채팅유저 등록
        combinePublicChatService.saveForPublicChatUser(publicChatUser);

        return "redirect:/publicChat/roomCode="+roomCode;
    }
}
