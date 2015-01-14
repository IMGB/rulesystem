package imgb.rulesystem.node.condition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import imgb.rulesystem.node.exception.InvalidJsonNodeKeyException;
import imgb.rulesystem.node.exception.NodeExecutionException;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期判定的condition node， 包含start time 和end time
 *
 */
public abstract class DateConditionNode extends ConditionNode {

	private static final Logger LOGGER = Logger.getLogger(DateConditionNode.class);

	public static final String START_TIME = "起始日期";
	public static final String END_TIME = "结束日期";

	private Date startDate = null;
	private Date endDate = null;

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/**
	 *
	 * @param startDate
	 *            开始日期字符串标示 可以为null
	 * @param endDate
	 *            结束日期字符串标示 可以为null
	 * @param dateFormat
	 *            转化为时间字符串的格式
	 * @throws NodeExecutionException
	 *             当解析字符串有问题时会抛出此异常
	 */
	public DateConditionNode(String startDate, String endDate, SimpleDateFormat dateFormat)
			throws NodeExecutionException {
		super();
		try {
			this.startDate = dateFormat.parse(startDate);
			this.endDate = dateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new NodeExecutionException(e.getMessage());
		}
	}

	/**
	 *
	 * @param startDate
	 *            开始日期 可以为null
	 * @param endDate
	 *            结束日期 可以为null
	 */
	public DateConditionNode(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public DateConditionNode() {
	}

	/**
	 * 比较时间 如果时间在begin time和end time 之间，返回true
	 * 否则返回false，当其中一个时间条件不存在时，忽略这个时间条件，只判断是否在begin time 之后或者end time 之前
	 * 
	 * @param date
	 *            输入的时间 如果为null 返回false；
	 * @return
	 */
	protected boolean compareDate(Date date) {
		if (date == null) {
			return false;
		}

		Boolean returnBoolean = true;
		if (startDate != null) {
			returnBoolean = date.after(startDate);
		}

		if (endDate != null) {
			returnBoolean = returnBoolean && date.before(endDate);
		}

		return returnBoolean;
	}

	@Override
	public JsonNode serlizationToJson() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String startTime = "";
		String endTime = "";
		if (startDate != null) {
			startTime += format.format(startDate);
		}
		if (endDate != null) {
			endTime += format.format(endDate);
		}
		ObjectNode rootNode = OBJECT_MAPPER.createObjectNode();
		rootNode.put("class", this.getClass().getName());
		ObjectNode valueJson = OBJECT_MAPPER.createObjectNode();
		valueJson.put("start_time", startTime);
		valueJson.put("end_time", endTime);
		rootNode.put("value", valueJson);
		return rootNode;
	}

	@Override
	public Object deserializeFromJson(JsonNode root) throws Exception {
		JsonNode valueNode = root.get("value");
		JsonNode startTimeNode = valueNode.get("start_time");
		JsonNode endTimeNode = valueNode.get("end_time");
		if (startTimeNode == null || endTimeNode == null) {
			throw new InvalidJsonNodeKeyException("both start time and end time nodes can not be null, the key must be miswritten");
		} else {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String startTime = startTimeNode.asText();
			String endTime = endTimeNode.asText();
			Date sd = format.parse(startTime);
			Date ed = format.parse(endTime);
			this.startDate = sd;
			this.endDate = ed;
		}
		return this;
	}
}
