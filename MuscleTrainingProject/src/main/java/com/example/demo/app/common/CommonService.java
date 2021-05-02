package com.example.demo.app.common;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ���ʏ�����Service�N���X�ł��B
 *
 * @author ���� 2021/4/10
 */
@Service
@Transactional
public class CommonService {
	
    /**
    * �Z�b�V�����^�C���A�E�g�̃`�F�b�N���s���܂��B
    *
    * @param session �Z�b�V�������
    * @return true:�Z�b�V�����^�C���A�E�g false:�Z�b�V��������
     */
   public boolean checkSessionTimeOut(HttpSession session) {

       if (session == null ||
               session.getAttribute(CommonConst.LOGIN_USER) == null) {
           return true;
       }

       return false;
   }

//    /** ����MyBatis�}�b�p�[ */
//    @Autowired
//    private CommonMapper commonMapper;
//
//    /**
//     * �W�������ꗗ���擾���܂��B
//     *
//     * @param session �Z�b�V�������
//     * @return �W�������ꗗ
//     */
//    @SuppressWarnings("unchecked")
//    public List<GenreInfo> getGenreList(HttpSession session) {
//
//        if (session.getAttribute(CommonConst.GET_GENRE_LIST) != null) {
//            // �Z�b�V�����Ɋi�[�ς݂̏ꍇ�́A���̃W�������ꗗ��ԋp
//            return (List<GenreInfo>) session.getAttribute(CommonConst.GET_GENRE_LIST);
//        }
//
//        // �W�������ꗗ���擾
//        List<GenreInfo> genreList = commonMapper.selectGenre();
//
//        // �W�������ꗗ���Z�b�V�����Ɋi�[
//        session.setAttribute(CommonConst.GET_GENRE_LIST, genreList);
//
//        return genreList;
//    }
//
//    /**
//     * �|�C���g���ވꗗ���擾���܂��B
//     *
//     * @param session �Z�b�V�������
//     * @return �|�C���g���ވꗗ
//     */
//    @SuppressWarnings("unchecked")
//    public List<PointCategoryInfo> getPointCategoryList(HttpSession session) {
//
//        if (session.getAttribute(CommonConst.GET_POINT_CATEGORY_LIST) != null) {
//            // �Z�b�V�����Ɋi�[�ς݂̏ꍇ�́A���̃W�������ꗗ��ԋp
//            return (List<PointCategoryInfo>) session.getAttribute(CommonConst.GET_POINT_CATEGORY_LIST);
//        }
//
//        // �|�C���g���ވꗗ���擾
//        List<PointCategoryInfo> pointCategoryList = commonMapper.selectPointCategory();
//
//        // �|�C���g���ވꗗ���Z�b�V�����Ɋi�[
//        session.setAttribute(CommonConst.GET_POINT_CATEGORY_LIST, pointCategoryList);
//
//        return pointCategoryList;
//    }
//
//    /**
//     * �Z�b�V�����^�C���A�E�g�̃`�F�b�N���s���܂��B
//     *
//     * @param session �Z�b�V�������
//     * @return true:�Z�b�V�����^�C���A�E�g false:�Z�b�V��������
//     */
//    public boolean checkSessionTimeOut(HttpSession session) {
//
//        if (session == null ||
//                session.getAttribute(CommonConst.LOGIN_USER) == null) {
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * �w��̃��[�U���Q�Ɖ\�ȏ��i�ꗗ���擾���܂��B
//     *
//     * @param userId ���[�UID�i�K�{���́j
//     * @param goodsId ���iID�inull�̏ꍇ�A�S���i�������j
//     * @param pointCategoryCode �|�C���g���ރR�[�h�inull�̏ꍇ�A�S���i�������j
//     * @param goodsCategory ���ރR�[�h�inull�̏ꍇ�A�S���i�������j
//     * @return ���i�ꗗ(�G���[�̏ꍇ��null��ԋp)
//     */
//    public List<GoodsInfo> getGoodsList(String userId, String goodsId,
//            String pointCategoryCode, String goodsCategory) {
//
//        // �����p�����[�^
//        String searchGoodsId = "%";
//        if (goodsId != null) {
//            searchGoodsId = goodsId;
//        }
//
//        String searchPoint = "0";
//        if (pointCategoryCode != null) {
//            searchPoint = getPointAmount(pointCategoryCode);
//        }
//
//        String searchGoodsCategory = "%";
//        if (goodsCategory != null) {
//            searchGoodsCategory = goodsCategory;
//        }
//
//        // ���i���擾
//        List<GoodsInfo> goodsInfoList = commonMapper.selectGoodsInfo(
//                userId, searchGoodsId, searchPoint, searchGoodsCategory);
//
//        // �����ő吔����
//        List<GoodsInfo> workGoodsData1 = checkMaxExchange(userId, goodsInfoList);
//        if (workGoodsData1 == null) {
//            return null;
//        }
//
//        // �����J���i�̐���
//        List<GoodsInfo> workGoodsData2 = checkGoodsPlan(workGoodsData1);
//        if (workGoodsData2 == null) {
//            return null;
//        }
//
//        // �L�����y�[������
//        List<GoodsInfo> goodsList = checkCampaign(workGoodsData2);
//
//        return goodsList;
//    }
//
//    /**
//     * �|�C���g���ރR�[�h�ɊY������|�C���g�����擾���܂��B
//     *
//     * @param pointCategoryCode �|�C���g���ރR�[�h
//     * @return �|�C���g��
//     */
//    private String getPointAmount(String pointCategoryCode) {
//
//        // �|�C���g���ރR�[�h�ɕR�Â��|�C���g�����擾
//        BigDecimal searchPoint = commonMapper.selectPointCategoryPoint(pointCategoryCode);
//        if (searchPoint == null) {
//            // �G���[�����������ꍇ
//            return null;
//        }
//
//        return searchPoint.toString();
//    }
//
//    /**
//     * �w��̏��i���X�g��������ő吔�𒴉߂������i�����O���ĕԂ��܂��B
//     *
//     * @param userId ���O�C�����[�UID
//     * @param goodsInfoList �����Ώۂ̏��i���X�g
//     * @return �L���ȏ��i���X�g(�G���[�̏ꍇ��null��ԋp)
//     */
//    private List<GoodsInfo> checkMaxExchange(String userId, List<GoodsInfo> goodsInfoList) {
//
//        List<GoodsInfo> result = new ArrayList<GoodsInfo>();
//
//        for (GoodsInfo goodsInfo : goodsInfoList) {
//
//            BigDecimal exchangeTotal = commonMapper.selectExchangeTotalUser(goodsInfo.getGoodsId(), userId);
//            if (exchangeTotal == null) {
//                // �G���[�����������ꍇ
//                return null;
//            }
//
//            if (goodsInfo.getMaxExchangeCount() != null) {
//
//                if ((exchangeTotal).compareTo(goodsInfo.getMaxExchangeCount()) == -1) {
//                    // ���[�U�̌����\����A�����m������A�����ő吔�������ꍇ
//                    result.add(goodsInfo);
//                }
//
//            } else {
//                // �����ő吔�����ݒ�̏��i
//                result.add(goodsInfo);
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * �w��̏��i���X�g���疢���J���i���������ĕԂ��܂��B
//     *
//     * @param goodsList �����Ώۂ̏��i���X�g
//     * @return �L���ȏ��i���X�g(�G���[�̏ꍇ��null��ԋp)
//     */
//    private List<GoodsInfo> checkGoodsPlan(List<GoodsInfo> goodsList) {
//
//        List<GoodsInfo> result = new ArrayList<GoodsInfo>();
//
//        for (GoodsInfo goodsInfo : goodsList) {
//
//            BigDecimal goodsPlanCount = commonMapper.selectGoodsPlan(goodsInfo.getGoodsId());
//            if (goodsPlanCount == null) {
//                // �G���[�����������ꍇ
//                return null;
//            }
//
//            if ((goodsPlanCount).compareTo(BigDecimal.ZERO) == 0) {
//                // �����J���i�łȂ��ꍇ
//                result.add(goodsInfo);
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * �w��̏��i���L�����y�[������ˍ����āA�L���ȏ��i�̂ݒ��o���܂��B
//     *
//     * @param goodsList �����Ώۂ̏��i���X�g
//     * @return �L���ȏ��i���X�g
//     */
//    private List<GoodsInfo> checkCampaign(List<GoodsInfo> goodsList) {
//
//        List<GoodsInfo> result = new ArrayList<GoodsInfo>();
//
//        for (GoodsInfo goodsInfo : goodsList) {
//
//            List<CampaignInfo> campaignInfoList = commonMapper.selectCampaign(goodsInfo.getGoodsId());
//
//            if (campaignInfoList.size() > 0) {
//                // �L�����y�[�����i�̏ꍇ
//
//                for (CampaignInfo campaignInfo : campaignInfoList) {
//                    Date startDate = campaignInfo.getCampaignStartDate();
//                    Date endDate = campaignInfo.getCampaignEndDate();
//                    Date currentDate = new Date();
//
//                    if (currentDate.compareTo(startDate) > 0 && currentDate.compareTo(endDate) < 0) {
//                        // �L�����y�[�����Ԃ̏ꍇ
//
//                        if (campaignInfo.getLimitCount() == null) {
//                            // ������������ꍇ
//                            result.add(goodsInfo);
//
//                        } else {
//                            // ��������`�F�b�N
//                            DateFormat df = new SimpleDateFormat("yyyyMMdd");
//
//                            BigDecimal totalAmountCampaign = commonMapper.selectTotalAmountCampaign(
//                                    goodsInfo.getGoodsId(), df.format(startDate), df.format(endDate));
//
//                            if (totalAmountCampaign != null) {
//
//                                if ((totalAmountCampaign).compareTo(campaignInfo.getLimitCount()) < 0) {
//                                    // ���������A������������ꍇ
//                                    result.add(goodsInfo);
//                                }
//
//                            } else {
//                                // �������̏ꍇ
//                                result.add(goodsInfo);
//                            }
//                        }
//                    }
//                }
//
//            } else {
//                // �L�����y�[�����i�łȂ��ꍇ
//                result.add(goodsInfo);
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * ���[�U�̏����|�C���g���擾���܂��B
//     *
//     * @param userId ���O�C�����[�UID
//     * @return ���[�U�̏����|�C���g
//     */
//    public MyPointInfo getMyPoint(String userId) {
//
//        MyPointInfo myPointInfo = commonMapper.selectMyPoint(userId);
//        if (myPointInfo == null) {
//            return null;
//        }
//
//        return myPointInfo;
//    }
//
//    /**
//     * ���[�U�������������i�̑�����Ԃ��܂��B
//     *
//     * @param userId ���O�C�����[�UID
//     * @param goodsId ���iID
//     * @return ���[�U�������������i�̑���
//     */
//    public BigDecimal getTotalExchangeCount(String userId, String goodsId) {
//
//        BigDecimal totalExchangeCount = commonMapper.selectExchangeTotalUser(goodsId, userId);
//        if (totalExchangeCount == null) {
//            return BigDecimal.ZERO;
//        }
//
//        return totalExchangeCount;
//    }
//
//    /**
//     * �w��̏��i���L�����y�[������ˍ����āA������Ƃ���܂ł̌��������v��Ԃ��܂��B
//     *
//     * @param goodsId ���iID
//     * @return [0]:������A[1]:���������v(�L�����y�[�����i�łȂ��ꍇ�͌������null)
//     */
//    public BigDecimal[] getCampaignInfo(String goodsId) {
//
//        // �L�����y�[�����̗L�����m�F
//        List<CampaignInfo> campaignInfoList = commonMapper.selectCampaign(goodsId);
//
//        BigDecimal[] result = new BigDecimal[2];
//
//        if (campaignInfoList.size() > 0) {
//            // �L�����y�[�����i�̏ꍇ
//
//            for (CampaignInfo campaignInfo : campaignInfoList) {
//                Date startDate = campaignInfo.getCampaignStartDate();
//                Date endDate = campaignInfo.getCampaignEndDate();
//                Date currentDate = new Date();
//
//                if (currentDate.compareTo(startDate) > 0 && currentDate.compareTo(endDate) < 0) {
//                    // �L�����y�[�����Ԃ̏ꍇ
//
//                    if (campaignInfo.getLimitCount() != null) {
//                        // ��������`�F�b�N
//                        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//
//                        BigDecimal totalAmountCampaign = commonMapper.selectTotalAmountCampaign(
//                                goodsId, df.format(startDate), df.format(endDate));
//
//                        if (totalAmountCampaign != null) {
//
//                            result[0] = campaignInfo.getLimitCount();
//                            result[1] = totalAmountCampaign;
//
//                        } else {
//                            // �������̏ꍇ
//                            result[0] = campaignInfo.getLimitCount();
//                            result[1] = BigDecimal.ZERO;
//                        }
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
}
