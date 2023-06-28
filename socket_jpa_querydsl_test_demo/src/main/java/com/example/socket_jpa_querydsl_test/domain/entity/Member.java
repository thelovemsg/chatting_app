package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.example.socket_jpa_querydsl_test.domain.entity.security.RefreshToken;
import com.example.socket_jpa_querydsl_test.domain.customenum.AddressType;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverterUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(uniqueConstraints =
    {
        @UniqueConstraint(columnNames = {"nickname"}),
        @UniqueConstraint(columnNames = {"phone_number"}),
        @UniqueConstraint(columnNames = {"email"})
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
public class Member extends BaseEntity implements Serializable {

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "user_own_id")
    private String userOwnId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @Column(name = "password")
    @Convert(converter = PasswordConverterUtil.class)
    private String password;

    @OneToMany(mappedBy = "member", fetch = EAGER)
    @Builder.Default
    private List<MemberRole> memberRoles = new ArrayList<>();

    @Column(name = "member_indv_id")
    private String memberIndividualId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<File> files = new ArrayList<>();

    public void addAddress(Address address){
        if(!addresses.contains(address))
            addresses.add(address);
    }

    public void removeAddress(Address address){
        if(addresses.contains(address))
            addresses.remove(address);
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BlockMember> blockingMembers;

    @OneToMany(mappedBy = "memberTarget", cascade = CascadeType.ALL)
    private List<BlockMember> blockedMembers;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Friend> friends;

    /**
     * @param blockMember
     * @descriptionn soft delete.
     * If you use orphanRemoval to delete related datas, it will really delete data in your table.
     * We're already using 'flag' column to check whether data is deleted or not.
     * So, we need to make new method for this.
     */
    public void removeBlockMember(BlockMember blockMember) {
        if (blockingMembers.contains(blockMember)) {
            blockMember.delete();
            blockingMembers.remove(blockMember);
        } else if (blockedMembers.contains(blockMember)) {
            blockMember.delete();
            blockedMembers.remove(blockMember);
        }
    }

    public static Member returnMemberWithId(Long memberId) {
        Member member = new Member();
        member.setId(memberId);
        return member;
    }

    public Member createMember(MemberSaveDto memberSaveDto){
        Address address = new Address();
        address.setAddress1(memberSaveDto.getAddress1());
        address.setAddress2(memberSaveDto.getAddress2());
        address.setAddressType(AddressType.PRIMARY);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        MemberRole memberRole = new MemberRole();
        memberRoles.add(memberRole);

        return new Member()
                .builder()
                .name(memberSaveDto.getName())
                .nickname(memberSaveDto.getNickname())
                .email(memberSaveDto.getEmail())
                .addresses(addresses)
                .memberRoles(memberRoles)
                .password(memberSaveDto.getPassword())
                .phoneNumber(memberSaveDto.getPhoneNumber())
                .build();
    }

}
