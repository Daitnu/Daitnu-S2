import Api from './Api';
import URL from './url';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';

export default class CategoryApi extends Api {
  constructor() {
    super();
  }

  public async getCategories(
    url = URL.CATEGORY,
  ): Promise<HTTPResponse<ResponseCategory[]> | BusinessErrorResponse> {
    return this.get<ResponseCategory[]>({ url });
  }
}
