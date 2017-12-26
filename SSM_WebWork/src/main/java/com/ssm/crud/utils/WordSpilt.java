package com.ssm.crud.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ��������˫�����ƥ��ִ��㷨ʵ�� 
 * @author ������
 * 
 */
public class WordSpilt {
	/**
	 * �洢�ִʴʵ�
	 */
	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * ����дʳ���,��Ϊ�������
	 */
	private int MAX_LENGTH = 5;

	/**
	 * ���췽���ж�ȡ�ִʴʵ䣬���洢��map��
	 * 
	 * @throws IOException
	 */
	public WordSpilt() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("D:\\Javaee\\javahhsdemo\\SSM_WebWork\\src\\main\\resources\\dict.txt"));
		String line = null;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			map.put(line, 0);
		}
		br.close();
	}

	/**
	 * ��������дʳ���
	 * 
	 * @param max
	 *            ����дʳ���
	 */
	public void setMaxLength(int max) {
		this.MAX_LENGTH = max;
	}
	/**
	 * ��ȡ��ǰ����дʳ��ȣ�Ĭ��Ϊ5��5�����֣�
	 * 
	 * @return ��ǰ����дʳ���
	 */
	public int getMaxLength() {
		return this.MAX_LENGTH;
	}
	/**
	 * ���ƥ��ִ��㷨
	 * 
	 * @param spiltStr
	 *            ���зֵ��ַ���
	 * @param leftToRight
	 *            �зַ���trueΪ�������ң�falseΪ��������
	 * @return �зֵ��ַ���
	 */
	public List<String> spilt(String spiltStr, boolean leftToRight) {
		// ������з��ַ���Ϊ���򷵻ؿ�
		if (spiltStr.isEmpty())
			return null;
		// ��������ƥ��ָ��ַ���
		List<String> leftWords = new ArrayList<String>();
		// ���渺��ƥ��ָ��ַ���
		List<String> rightWords = new ArrayList<String>();
		// ����ȡ�зֵ��ִ�
		String word = null;
		// ȡ�ʵĳ��ȣ���ʼ������Ϊ���ֵ
		int wordLength = MAX_LENGTH;
		// �ִʲ����д����ִ���ǰλ��
		int position = 0;
		// �Ѿ������ַ����ĳ���
		int length = 0;
		// ȥ���ַ����ж���Ŀո�
		spiltStr = spiltStr.trim().replaceAll("\\s+", "");
		// �����з��ַ���û�б��з���ʱѭ���з�
		while (length < spiltStr.length()) {
			// �����û�зֵ��ַ�������С�����ֵ����ȡ�ʴʳ����ڸôʱ�����
			if (spiltStr.length() - length < MAX_LENGTH)
				wordLength = spiltStr.length() - length;
			// ����ȡĬ��ֵ
			else
				wordLength = MAX_LENGTH;
			// ������������ƥ�䣬��spiltStr��position����ʼ�и�
			if (leftToRight) {
				position = length;
				word = spiltStr.substring(position, position + wordLength);
			}
			// ������������ƥ�䣬��spiltStrĩβ��ʼ�и�
			else {
				position = spiltStr.length() - length;
				word = spiltStr.substring(position - wordLength, position);
			}
			// �ӵ�ǰλ�ÿ�ʼ�и�ָ�����ȵ��ַ���
			// word = spiltStr.substring(position, position + wordLength);

			// ����ִʴʵ�����û���и�������ַ�������ȥһ���ַ�
			while (!map.containsKey(word)) {
				// ����ǵ��֣��˳�ѭ��
				if (word.length() == 1) {
					// �������ĸ��������Ҫ����������ĸ�������ַ���һ��
					if (word.matches("[a-zA-z0-9]")) {
						// ���������ƥ��ֱ��ѭ�������������ַ�������
						if (leftToRight) {
							for (int i = spiltStr.indexOf(word, position) + 1; i < spiltStr
									.length(); i++) {
								if ((spiltStr.charAt(i) >= '0' && spiltStr
										.charAt(i) <= '9')
										|| (spiltStr.charAt(i) >= 'A' && spiltStr
												.charAt(i) <= 'Z')
										|| (spiltStr.charAt(i) >= 'a' && spiltStr
												.charAt(i) <= 'z')) {
									word += spiltStr.charAt(i);
								} else
									break;
							}
						} else {
							// ���������ƥ�䣬�ӵ�ǰλ��֮ǰ���������֡���ĸ�ַ�����������ת
							for (int i = spiltStr.indexOf(word, position - 1) - 1; i >= 0; i--) {
								if ((spiltStr.charAt(i) >= '0' && spiltStr
										.charAt(i) <= '9')
										|| (spiltStr.charAt(i) >= 'A' && spiltStr
												.charAt(i) <= 'Z')
										|| (spiltStr.charAt(i) >= 'a' && spiltStr
												.charAt(i) <= 'z')) {
									word += spiltStr.charAt(i);
									if (i == 0) {
										StringBuffer sb = new StringBuffer(word);
										word = sb.reverse().toString();
									}
								} else {
									// ��ת����
									StringBuffer sb = new StringBuffer(word);
									word = sb.reverse().toString();
									break;
								}
							}
						}
					}
					break;
				}
				// ������������ƥ�䣬��ȥ���һ���ַ�
				if (leftToRight)
					word = word.substring(0, word.length() - 1);
				// ������ȥ��һ���ַ�
				else
					word = word.substring(1);
			}
			// ���зֳ������ַ����浽ָ���ı���
			if (leftToRight)
				leftWords.add(word);
			else
				rightWords.add(word);
			// �Ѵ����ַ�������
			length += word.length();
		}
		// ������������ƥ�䣬Ҫ�ѱ��е��ַ�������Ϊ����
		if (!leftToRight) {
			for (int i = rightWords.size() - 1; i >= 0; i--) {
				leftWords.add(rightWords.get(i));
			}
		}
		// �����зֽ��
		return leftWords;
	}

	/**
	 * �ж����������Ƿ����
	 * @param list1          ����1
	 * @param list2     ����2
	 * @return �������򷵻�true������Ϊfalse
	 */
	public boolean isEqual(List<String> list1, List<String> list2) {
		if (list1.isEmpty() && list2.isEmpty())
			return false;
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i)))
				return false;
		}
		return true;
	}

	/**
	 * �б�ִ����庯��
	 * 
	 * @param inputStr
	 *            ���з��ַ���
	 * @return �ִʽ��
	 */
	public List<String> resultWord(String inputStr) {
		// �ִʽ��
		List<String> result = new ArrayList<String>();
		// ����̰���ߡ��ִʽ��
		List<String> resultLeft = new ArrayList<String>();
		// ����̰���ߡ������粿�֣��ִʽ��
		List<String> resultMiddle = new ArrayList<String>();
		// ����̰���ߡ��ִʽ��
		List<String> resultRight = new ArrayList<String>();
		// �������ƥ��ִʽ��
		List<String> left = new ArrayList<String>();
		// �������ƥ��ִʽ��
		List<String> right = new ArrayList<String>();
		left = spilt(inputStr, true);
		/*System.out.println("����ִʽ����");
		for (String string : left) {
			System.out.print(string + "/");
		}
		System.out.println("\n����ִʽ����");*/
		right = spilt(inputStr, false);
		/*for (String string : right) {
			System.out.print(string + "/");
		}
		System.out.println("\n˫��ִʽ����");*/
		// �ж���ͷ�ķִ�ƴ�ӣ��Ƿ��Ѿ��������ַ������м佻�㣬ֻҪû�н��㣬�Ͳ�ͣѭ��
		while (left.get(0).length() + right.get(right.size() - 1).length() < inputStr
				.length()) {
			// ���������ִʽ����ȣ���ôȡ����������ѭ��
			if (isEqual(left, right)) {
				resultMiddle = left;
				break;
			}
			// ���������ִʽ����ͬ����ȡ�ִ��������ٵ��Ǹ���������ѭ��
			if (left.size() != right.size()) {
				resultMiddle = left.size() < right.size() ? left : right;
				break;
			}
			// ������������������ϣ���ôʵ�С�̰���ߡ��㷨
			// �á���̰���ߡ���������ִʽ���ĵ�һ����
			resultLeft.add(left.get(0));
			// �á���̰���ߡ���������ִʽ�������һ����
			resultRight.add(right.get(right.size() - 1));
			// ȥ������̰���ߡ��Ե��Ĵ���
			inputStr = inputStr.substring(left.get(0).length());
			inputStr = inputStr.substring(0,
					inputStr.length() - right.get(right.size() - 1).length());
			// ����֮ǰ������ִʽ������ֹ��ɸ���
			left.clear();
			right.clear();
			// ��û���Ե����ַ������¿�ʼ�ִ�
			left = spilt(inputStr, true);
			right = spilt(inputStr, false);
		}
		// ѭ��������˵��Ҫô�ִ�û�������ˣ�Ҫô"̰����"����ͷ�Ե��м佻����
		// ��������м佻�㣬����ʱ�ķִʽ������Ҫ���������жϣ�
		// ����м佻�����ص��ˣ�
		// �����һ���ִʵĳ��� + �������һ���ִʵĳ��� > �����ַ����ܳ��ȣ���ֱ��ȡ�����
		if (left.get(0).length() + right.get(right.size() - 1).length() > inputStr
				.length())
			resultMiddle = left;
		// ����м佻�㣬�պó��꣬û���ص���
		// �����һ���ִ� + �������һ���ִʵĳ��� = �����ַ����ܳ��ȣ���ô������һƴ����
		if (left.get(0).length() + right.get(right.size() - 1).length() == inputStr
				.length()) {
			resultMiddle.add(left.get(0));
			resultMiddle.add(right.get(right.size() - 1));
		}
		// ��û������ķִʽ����ӵ����ս��result��
		for (String string : resultLeft) {
			result.add(string);
		}
		for (String string : resultMiddle) {
			result.add(string);
		}
		// ����̰���ߡ��洢�ķִ�Ҫ����Ϊ����
		for (int i = resultRight.size() - 1; i >= 0; i--) {
			result.add(resultRight.get(i));
		}
		return result;
	}

	/**
	 * ��һ�λ��ָ�����ɾ仰���ֱ���зִ�
	 * 
	 * @param inputStr
	 *            ���ָ��һ�λ�
	 * @return ��λ��ķִʽ��
	 */
	public List<String> resultSpilt(String inputStr) {
		// ���ڴ洢���շִʽ��
		List<String> result = new ArrayList<String>();
		// ����������ͷָ�����ɾ仰
		String regex = "[����������]";
		String[] st = inputStr.split(regex);
		// ��ÿһ�仰�ķִʽ����ӵ����շִʽ����
		for (String stri : st) {
			List<String> list = resultWord(stri);
			result.addAll(list);
		}
		return result;
	}

	
}
