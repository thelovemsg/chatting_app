package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Convert(converter = PasswordConverter.class)
    private String password;

//    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)

    @Builder.Default
    private List<File> files = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BlockMember> blockingMembers;

    @OneToMany(mappedBy = "memberTarget", cascade = CascadeType.ALL)
    private List<BlockMember> blockedMembers;

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

    public static Member createMember(MemberSaveDto memberSaveDto){
        Address address = new Address();
        address.setAddress1(memberSaveDto.getAddress1());
        address.setAddress2(memberSaveDto.getAddress2());
        address.setAddressStatus(AddressStatus.PRIMARY);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        return new Member()
                .builder()
                .name(memberSaveDto.getName())
                .nickname(memberSaveDto.getNickname())
                .email(memberSaveDto.getEmail())
                .addresses(addresses)
                .password(memberSaveDto.getPassword())
                .phoneNumber(memberSaveDto.getPhoneNumber())
                .build();
    }

}
