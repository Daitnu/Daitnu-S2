import Api from './Api';
import URL from './url';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';
import {
  AddCategoryParam,
  RenameCategoryParam,
  DeleteCategoryParam,
} from '~/@types/request/category';

export default class CategoryApi extends Api {
  constructor() {
    super();
  }

  public async getCategories(
    url = URL.CATEGORY,
  ): Promise<HTTPResponse<ResponseCategory[]> | BusinessErrorResponse> {
    return this.get<ResponseCategory[]>({ url });
  }

  public async addCategory(
    data: AddCategoryParam,
    url = URL.CATEGORY,
  ): Promise<HTTPResponse<ResponseCategory> | BusinessErrorResponse> {
    return this.post<ResponseCategory, AddCategoryParam>({ url, data });
  }

  public async renameCategory(
    data: RenameCategoryParam,
    url = URL.CATEGORY,
  ): Promise<HTTPResponse<ResponseCategory> | BusinessErrorResponse> {
    return this.patch<ResponseCategory, RenameCategoryParam>({ url, data });
  }

  public async deleteCategory(
    data: DeleteCategoryParam,
    url = URL.CATEGORY,
  ): Promise<HTTPResponse<ResponseCategory> | BusinessErrorResponse> {
    return this.delete<ResponseCategory, DeleteCategoryParam>({ url, data });
  }
}
