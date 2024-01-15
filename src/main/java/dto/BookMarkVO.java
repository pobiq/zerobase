package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkVO {
	private int id;
	private String name;
	private String main_nm;
	private String regist_date;
	private String mgr_no;
}
