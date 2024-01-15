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
public class BookMarkGroupVO {
	private int id;
	private String name;
	private int preference;
	private String regist_date;
	private String update_date;
}
