package com.jobs.luckystage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NoticesPageResponseDTO<E> {
    private int page; // 현재 페이지
    private int size; //블록크기
    private int total; // 전체 레코드 수
    private int start; //시작페이지 번호
    private int end;  // 끝페이지 번호
    private boolean prev; // 이전페이지 존재 여부
    private boolean next; // 다음 페이지 존재 여부
    private List<E> dtoList; // 페이징할 dto 리스트
    @Builder(builderClassName = "withAll")
    public NoticesPageResponseDTO(NoticesPageRequestDTO noticesPageRequestDTO,
                                  List<E> dtoList,
                                  int total) {
        if(total<=0){
            return;
        }
        this.page=noticesPageRequestDTO.getPage();
        this.size=noticesPageRequestDTO.getSize();
        this.total=total;
        this.dtoList=dtoList;

        this.end=(int)(Math.ceil(this.page/(double)size))*size;
        this.start=this.end-this.size+1;
        int last=(int)(Math.ceil(total/(double)size));
        this.end=end>last?last:end;     //마지막 블록은 last가 end가 되도록 설정

        this.prev=this.start>1;
        this.next=total>this.end * this.size;
    }
}
