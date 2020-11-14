package com.ifelseco.producemessage;


import com.ifelseco.producemessage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendMessageController {

    private MessageService messageService;


    public SendMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity sendMessage(@RequestParam("msg") String msg){
        if(!msg.equals("")) {

            try {
                messageService.sendMessage(msg);
                return new ResponseEntity("Message successfuly sent.",HttpStatus.OK);

            }catch(Exception e) {
                return new ResponseEntity("Error:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }else {
            return new ResponseEntity("Empty message",HttpStatus.BAD_REQUEST);
        }
    }
}
