import { CATEGORY_RENAME } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseCategory } from '~/@types/response/category';

const categoryRenameReducer = makeApiReducer<ResponseCategory>(CATEGORY_RENAME);

export default categoryRenameReducer;
