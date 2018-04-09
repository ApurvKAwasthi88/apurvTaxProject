# The Tax Project.It helps the client to compute taxes for various countries based on their Tax Rules and generates a receipt for items provided to the api
The TaxOrchestrator is the main class for the api. It computes taxes and generates recipt based on the Items provided to the the Orchestrator. Orchestrator computes taxes based on country, it calls the TaxFactory to get the instaice of TaxBusiness.

We are loading the tax rates and addiing it to the cache. The cache gets loaded from the property file(tax.prop), this can be chnaged to load it from the database from a meatdat table that stores Item category and its tax rate.
Guava Cahe is used in the implmentaion in TaxRepo so if there is a cahce miss it try to load it from the datasorce so this can be helpful in the use cases of when we add new tax rates for new itemes. We can also have an evivtion policy by which the item get evicited after particular time and the cahce loads it from the datasource this will be helpfull when we update some of the tax rate from the backend.







## Code Coverage
Tool Used : InteliJ

![taxprojectcodecoverage](https://user-images.githubusercontent.com/38209163/38498746-0b065a82-3c23-11e8-881e-5b25c6ff4391.jpg)

Please note: There are some defualt implementation that are empty and are created from the extensibility prespective. Hecne in some of the packages the coverage is low

