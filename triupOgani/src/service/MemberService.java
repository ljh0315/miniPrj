package service;

import sl063.exception.RecordDuplicatedException;
import sl063.exception.RecordNotFoundException;
import vo.MemberVO;
import entity.MemberEntity;

/**
 * This object performs a variety of member registration services.
 * I tacts a Facade into the business logic of registering a Member
 */
public class MemberService {
    private MemberEntity memberDataAccess = null;
    /**
     * This constructor creates a Registration Service object.
     */
    public MemberService() {
        memberDataAccess = new MemberEntity();
    }
    
    public MemberVO getMember(String memID) {
        System.out.println("getMember()  " + memID);
        return new MemberVO(memID);
    }//getMember
    
    public MemberVO register(MemberVO member) throws RecordDuplicatedException {
        //public Member register(Member member) {
        memberDataAccess.insertMember(member);
        //return select(member.getMemID());
        return member;
    }//register
    
    public MemberVO select(String memId) throws RecordNotFoundException {
        MemberVO member =  memberDataAccess.selectMember(memId);
        return member;
    }//select
    
    public MemberVO update(MemberVO member) throws RecordNotFoundException {
        memberDataAccess.updateMember(member);
        //return select(member.getMemID());
        return member;
    }//update
    
    public MemberVO compareID(String usrID,String Passwd) {
        MemberVO member =  memberDataAccess.compareID(usrID,Passwd);
        return member;
    }
    
    
}
