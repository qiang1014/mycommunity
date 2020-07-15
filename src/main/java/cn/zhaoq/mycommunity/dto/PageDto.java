package cn.zhaoq.mycommunity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 给视图层返回展示信息，包括页码
 */

@Data
public class PageDto {
    private List<QuestionDto> questionDtos;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPageDto(Integer totalCount,Integer page,Integer size){
        this.page = page;

        Integer totalPage = 0;
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        this.totalPage = totalPage;
        //计算pages
        pages.add(page);
        for(int i=1;i<3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }

            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //判断是否有前一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }

        //判断是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //判断是否有下一页
        if(page==totalPage){
            showNext = false;
        }else {
            showNext = true;
        }

        //判断是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }

}
