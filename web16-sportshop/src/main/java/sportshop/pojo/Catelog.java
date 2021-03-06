package sportshop.pojo;

/**
 * 商品类别
 * 
 * @author lujun
 * 
 */
public class Catelog {
	private int catelog_id;
	private String catelog_name;
	private String catelog_miaoshu;

	public Catelog(int catelog_id) {
		this.catelog_id = catelog_id;
	}

	public Catelog(int catelog_id, String catelog_name, String catelog_miaoshu) {
		this.catelog_id = catelog_id;
		this.catelog_name = catelog_name;
		this.catelog_miaoshu = catelog_miaoshu;
	}

	public Catelog() {
	}

	public int getCatelog_id() {
		return catelog_id;
	}

	public void setCatelog_id(int catelog_id) {
		this.catelog_id = catelog_id;
	}

	public String getCatelog_name() {
		return catelog_name;
	}

	public void setCatelog_name(String catelog_name) {
		this.catelog_name = catelog_name;
	}

	public String getCatelog_miaoshu() {
		return catelog_miaoshu;
	}

	public void setCatelog_miaoshu(String catelog_miaoshu) {
		this.catelog_miaoshu = catelog_miaoshu;
	}

}
